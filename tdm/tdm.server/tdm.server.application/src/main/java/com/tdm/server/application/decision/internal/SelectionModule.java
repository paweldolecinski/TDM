package com.tdm.server.application.decision.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.preferences.FuzzyPreferenceRelation;
import com.tdm.domain.model.preferences.SolutionIdeaTupleWithValue;
import com.tdm.domain.model.problem.GdmProblemId;

public class SelectionModule {

	/**
	 * aggregation phase TODO: move to preferences module
	 */
	public FuzzyPreferenceRelation getCollectivePreference(
			GdmProblemId problemId, List<FuzzyPreferenceRelation> preferences) {

		FuzzyPreferenceRelation collectivePref = new FuzzyPreferenceRelation();
		if (preferences.isEmpty()) {
			return collectivePref;
		}
		OperatorOwa owa = new OperatorOwa(0.13, 0.17, 0.2, 0.5);
		FuzzyPreferenceRelation ideaTuples = preferences.get(0);
		for (SolutionIdeaTupleWithValue solutionIdeaTuple : ideaTuples) {
			List<Double> values = new ArrayList<>();
			for (FuzzyPreferenceRelation fuzzyPreferenceRelation : preferences) {
				SolutionIdeaTupleWithValue tuple = fuzzyPreferenceRelation
						.get(solutionIdeaTuple);
				if (tuple != null) {
					values.add(tuple.getValue());
				}
			}
			collectivePref.add(new SolutionIdeaTupleWithValue(
					solutionIdeaTuple, owa.calculate(values)));
		}
		return collectivePref;
	}

	public void calculateConsensus(GdmProblemId problemId,
			List<ExpertId> experts, List<SolutionIdeaId> ideas,
			List<SolutionIdeaId> globalRanking) {
		
		//p_i(x_j) = ( |V^c - V^i| / n-1 )^b
		
		// C(x_j) = 1 - sum (p_i(x_j)/m) m to liczba ekspertow
		
		//C_X = (1-beta)* sum
		
	}

	public List<SolutionIdeaId> getGlobalRanking(List<SolutionIdeaId> ideas,
			FuzzyPreferenceRelation collectivePreference) {
		Map<Double, SolutionIdeaId> qgdd = calculateQGDD(ideas,
				collectivePreference);
		ArrayList<Double> keys = new ArrayList<>(qgdd.keySet());
		Collections.sort(keys);
		Collections.reverse(keys);

		ArrayList<SolutionIdeaId> ranking = new ArrayList<>();
		for (Double key : keys) {
			ranking.add(qgdd.get(key));
		}

		return ranking;
	}

	private Map<Double, SolutionIdeaId> calculateQGDD(
			List<SolutionIdeaId> ideas,
			FuzzyPreferenceRelation collectivePreference) {
		Map<Double, SolutionIdeaId> qgdd = new HashMap<>();
		OperatorOwa owa = new OperatorOwa(0.07, 0.67, 0.26);
		for (SolutionIdeaId idea1 : ideas) {
			List<Double> values = new ArrayList<>();
			for (SolutionIdeaId idea2 : ideas) {
				if (idea1.equals(idea2)) {
					continue;
				}
				values.add(collectivePreference.getValue(idea1, idea2));
			}
			double owaV = owa.calculate(values);
			qgdd.put(owaV, idea1);
		}
		return qgdd;
	}
}
