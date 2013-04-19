package com.tdm.domain.model.problem.dto;

import com.tdm.domain.model.preferences.dto.FuzzyPreferences;

public class GdmProblemCurrentConsensus {

    private double consensusLevel;
    private String problemId;
    private FuzzyPreferences globalPreferences;

    public double getConsensusLevel() {
	return consensusLevel;
    }

    public String getProblemId() {
	return problemId;
    }

    public FuzzyPreferences getGlobalPreferences() {
	return globalPreferences;
    }
}
