package com.tdm.server.application.decision.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.server.application.Broadcaster;
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation;
import com.tdm.server.application.decision.preference.SolutionIdeaUtilityList;
import com.tdm.server.application.decision.preferences.UnifyingPreferences;
import com.tdm.server.application.decision.process.SelectionModule;

@Service
public class DecisionProcessService {

	@Autowired
	ProblemRepository problemDao;
	@Autowired
	Broadcaster broadcaster;

	public void vote(ProblemId problemId, SolutionIdeaUtilityList preferences) {

		FuzzyPreferenceRelation uniPrefs = UnifyingPreferences
				.transform(preferences);
		// TODO: update DB with new expert preferences

		broadcaster.publish(problemId, Broadcaster.Message.NEW_RESULT);
	}

	public List<SolutionIdeaId> getCurrentResult(ProblemId problemId) {
		Problem problem = problemDao.read(problemId);

		// TODO: retrieve all preferences
		ArrayList<FuzzyPreferenceRelation> allPrefs = new ArrayList<FuzzyPreferenceRelation>();
		// allPrefs.add(uniPrefs);

		FuzzyPreferenceRelation collective = SelectionModule
				.getCollectivePreference(allPrefs);

		// TODO: retrieve ideas
		List<SolutionIdeaId> ranking = SelectionModule.getGlobalRanking(
				new ArrayList<SolutionIdeaId>(), collective);
		return ranking;
	}
}
