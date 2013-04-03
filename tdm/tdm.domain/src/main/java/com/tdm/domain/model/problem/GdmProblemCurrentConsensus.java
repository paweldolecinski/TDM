package com.tdm.domain.model.problem;

import com.tdm.domain.model.preferences.FuzzyPreferences;

public class GdmProblemCurrentConsensus {

	private double consensusLevel;
	private GdmProblemId problemId;
	private FuzzyPreferences globalPreferences;

	public double getConsensusLevel() {
		return consensusLevel;
	}

	public GdmProblemId getProblemId() {
		return problemId;
	}

	public FuzzyPreferences getGlobalPreferences() {
		return globalPreferences;
	}
}
