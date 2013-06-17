package com.tdm.server.application.decision.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.preferences.IdeaPreference;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.server.application.Broadcaster;
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation;
import com.tdm.server.application.decision.preference.SolutionIdeaNote;
import com.tdm.server.application.decision.preference.SolutionIdeaTupleWithValue;
import com.tdm.server.application.decision.preference.SolutionIdeaUtilityList;
import com.tdm.server.application.decision.preferences.UnifyingPreferences;
import com.tdm.server.application.decision.process.SelectionModule;

@Service
public class DecisionProcessService {

	@Autowired
	ProblemRepository problemDao;
	@Autowired
	SolutionIdeaRepository solutionDao;
	@Autowired
	Broadcaster broadcaster;

	public void vote(ProblemId problemId, ExpertId expertId,
			SolutionIdeaUtilityList preferences) {

		Problem problem = problemDao.readFor(problemId, expertId);

		if (problem == null) {
			throw new IllegalStateException(
					"Cannot vote on problem: expert not in discusion set.");
		}
		Expert expert = loopupExpert(expertId, problem);

		List<IdeaPreference> currentPreferences = new ArrayList<IdeaPreference>(
				expert.getCurrentPreferences());

		for (SolutionIdeaNote newPref : preferences) {
			int prefVal = newPref.getNote();

			if (prefVal != -1) {
				IdeaPreference p = new IdeaPreference(newPref.getSolutionId()
						.getIdString(), prefVal);
				if (currentPreferences.contains(p)) {
					IdeaPreference old = currentPreferences
							.get(currentPreferences.indexOf(p));
					old.setValue(prefVal);
				} else {
					currentPreferences.add(p);
				}
			}
		}
		problemDao.update(expert, currentPreferences);

		broadcaster.publish(problemId, Broadcaster.Message.NEW_RESULT);
	}

	private Expert loopupExpert(ExpertId expertId, Problem problem) {
		for (Expert ex : problem.getExperts()) {
			if (ex.getId().equals(expertId.getIdString())) {
				return ex;
			}
		}
		throw new IllegalStateException("Expert not found.");
	}

	public int getCurrentConsensus(ProblemId problemId) {
		Problem problem = problemDao.read(problemId);
		ListMultimap<Key, Double> multimap = ArrayListMultimap.create();

		for (Expert expert : problem.getExperts()) {
			Set<IdeaPreference> currentPreferences = expert
					.getCurrentPreferences();
			for (IdeaPreference ideaPreference : currentPreferences) {
				multimap.put(ideaPreference.getSolution(),
						ideaPreference.getProximity());
			}
		}

		float cX = 0;
		Set<Key> keySet = multimap.keySet();
		for (Key x : keySet) {
			List<Double> p = multimap.get(x);
			double c = 0;
			for (Double pk : p) {
				c += pk / p.size();
			}
			c = 1 - c;
			cX += (float) c / keySet.size();
		}
		// cX *= 0.5;
		return Math.round(cX * 100);
	}

	public List<SolutionIdeaId> getCurrentResult(ProblemId problemId,
			ExpertId expertId) {
		Problem problem = problemDao.read(problemId);

		ArrayList<FuzzyPreferenceRelation> allPrefs = getAllPrefs(problem);

		FuzzyPreferenceRelation collective = SelectionModule
				.getCollectivePreference(allPrefs);

		HashSet<SolutionIdeaId> ideaIds = new HashSet<SolutionIdeaId>();
		for (SolutionIdeaTupleWithValue tuple : collective) {
			ideaIds.add(tuple.getLeft());
			ideaIds.add(tuple.getRight());
		}

		List<SolutionIdeaId> gloablRanking = SelectionModule.getGlobalRanking(
				new ArrayList<SolutionIdeaId>(ideaIds), collective);

		calcProximity(expertId, problem, gloablRanking);

		return gloablRanking;
	}

	private void calcProximity(ExpertId expertId, Problem problem,
			List<SolutionIdeaId> gloablRanking) {
		for (Expert expert : problem.getExperts()) {

			List<IdeaPreference> currentPreferences = new ArrayList<IdeaPreference>(
					expert.getCurrentPreferences());

			FuzzyPreferenceRelation uniPrefs = provideUnifiedCurrentPrefs(currentPreferences);

			HashSet<SolutionIdeaId> ideaIds = new HashSet<SolutionIdeaId>();
			for (SolutionIdeaTupleWithValue tuple : uniPrefs) {
				ideaIds.add(tuple.getLeft());
				ideaIds.add(tuple.getRight());
			}
			List<SolutionIdeaId> expertRanking = SelectionModule
					.getGlobalRanking(new ArrayList<SolutionIdeaId>(ideaIds),
							uniPrefs);

			for (SolutionIdeaId solutionIdeaId : ideaIds) {
				int vC = gloablRanking.indexOf(solutionIdeaId) + 1;
				int vK = expertRanking.indexOf(solutionIdeaId) + 1;
				double b = 0.7;
				double p = 0;
				if (vC == 0) {
					p = 1.0;
				} else {
					int abs = Math.abs(vC - vK);
					if (ideaIds.size() - 1 > 0) {
						float i = (float) abs / (ideaIds.size() - 1);
						p = Math.pow(i, b);
					}
				}
				IdeaPreference id = new IdeaPreference(
						solutionIdeaId.getIdString(), -1);
				currentPreferences.get(currentPreferences.indexOf(id))
						.setProximity(p);
			}
			problemDao.update(expert, currentPreferences);
		}
	}

	private ArrayList<FuzzyPreferenceRelation> getAllPrefs(Problem problem) {
		ArrayList<FuzzyPreferenceRelation> allPrefs = new ArrayList<FuzzyPreferenceRelation>();

		for (Expert expert : problem.getExperts()) {
			Set<IdeaPreference> currentPreferences = expert
					.getCurrentPreferences();
			if (currentPreferences != null) {
				FuzzyPreferenceRelation uniPrefs = provideUnifiedCurrentPrefs(currentPreferences);
				allPrefs.add(uniPrefs);
			}
		}
		return allPrefs;
	}

	private FuzzyPreferenceRelation provideUnifiedCurrentPrefs(
			Collection<IdeaPreference> currentPreferences) {
		SolutionIdeaUtilityList notes = new SolutionIdeaUtilityList();
		for (IdeaPreference p : currentPreferences) {
			SolutionIdeaNote tuple = new SolutionIdeaNote(new SolutionIdeaId(
					KeyFactory.keyToString(p.getSolution())), p.getValue());
			notes.add(tuple);
		}
		FuzzyPreferenceRelation uniPrefs = UnifyingPreferences.transform(notes);
		return uniPrefs;
	}
}
