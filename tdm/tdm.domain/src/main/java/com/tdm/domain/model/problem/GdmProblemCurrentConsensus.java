package com.tdm.domain.model.problem;

import com.tdm.domain.model.preferences.FuzzyPreferenceRelation;

public class GdmProblemCurrentConsensus {

	private double consensusLevel;
	private GdmProblemId problemId;
	private FuzzyPreferenceRelation globalPreferences;

	public double getConsensusLevel() {
		return consensusLevel;
	}

	public GdmProblemId getProblemId() {
		return problemId;
	}

	public FuzzyPreferenceRelation getGlobalPreferences() {
		return globalPreferences;
	}
}
