package com.tdm.server.application.decision.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.KeyFactory;
import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.preferences.IdeaPairPreferences;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.server.application.Broadcaster;
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation;
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

		FuzzyPreferenceRelation uniPrefs = UnifyingPreferences
				.transform(preferences);

		List<IdeaPairPreferences> currentPreferences = expert
				.getCurrentPreferences();
		if (currentPreferences == null) {
			currentPreferences = new ArrayList<IdeaPairPreferences>();
		}

		for (SolutionIdeaTupleWithValue newPref : uniPrefs) {
			double prefVal = newPref.getValue();
			if (prefVal != -1) {
				IdeaPairPreferences p = new IdeaPairPreferences(newPref
						.getLeft().getIdString(), newPref.getRight()
						.getIdString(), prefVal);
				if (currentPreferences.contains(p)) {
					IdeaPairPreferences old = currentPreferences
							.get(currentPreferences.indexOf(p));
					old.setValue(prefVal);
				} else {
					currentPreferences.add(p);
				}
			}
		}
		expert.setCurrentPreferences(currentPreferences);
		problemDao.update(problem);

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

	public List<SolutionIdeaId> getCurrentResult(ProblemId problemId) {
		Problem problem = problemDao.read(problemId);

		ArrayList<FuzzyPreferenceRelation> allPrefs = new ArrayList<FuzzyPreferenceRelation>();

		for (Expert expert : problem.getExperts()) {
			List<IdeaPairPreferences> currentPreferences = expert
					.getCurrentPreferences();
			if (currentPreferences != null) {
				FuzzyPreferenceRelation uniPrefs = new FuzzyPreferenceRelation();
				for (IdeaPairPreferences p : currentPreferences) {
					SolutionIdeaTupleWithValue tuple = new SolutionIdeaTupleWithValue(
							new SolutionIdeaId(KeyFactory.keyToString(p.getLeftSolution())),
							new SolutionIdeaId(KeyFactory.keyToString(p.getRightSolution())),
							p.getValue());
					uniPrefs.add(tuple);
				}
				allPrefs.add(uniPrefs);
			}
		}

		FuzzyPreferenceRelation collective = SelectionModule
				.getCollectivePreference(allPrefs);

		HashSet<SolutionIdeaId> ideaIds = new HashSet<SolutionIdeaId>();
		for (SolutionIdeaTupleWithValue tuple : collective) {
			ideaIds.add(tuple.getLeft());
			ideaIds.add(tuple.getRight());
		}

		List<SolutionIdeaId> ranking = SelectionModule.getGlobalRanking(
				new ArrayList<SolutionIdeaId>(ideaIds), collective);
		return ranking;
	}
}
