package com.tdm.domain.model.problem.dto;

import com.tdm.domain.model.preferences.dto.SolutionPreferences;

public class CurrentConsensus {

	private double consensusLevel;
	private String problemId;
	private SolutionPreferences globalPreferences;

	public double getConsensusLevel() {
		return consensusLevel;
	}

	public String getProblemId() {
		return problemId;
	}

	public SolutionPreferences getGlobalPreferences() {
		return globalPreferences;
	}
}
