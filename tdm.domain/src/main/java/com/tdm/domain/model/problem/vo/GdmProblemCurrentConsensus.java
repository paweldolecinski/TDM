package com.tdm.domain.model.problem.vo;

import com.tdm.domain.model.preferences.vo.FuzzyPreferences;

public class GdmProblemCurrentConsensus {

	private double consensusLevel;
	private GdmProblemKey problemId;
	private FuzzyPreferences globalPreferences;

	public double getConsensusLevel() {
		return consensusLevel;
	}

	public GdmProblemKey getProblemId() {
		return problemId;
	}

	public FuzzyPreferences getGlobalPreferences() {
		return globalPreferences;
	}
}
