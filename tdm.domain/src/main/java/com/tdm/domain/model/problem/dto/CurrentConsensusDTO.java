package com.tdm.domain.model.problem.dto;

import java.util.List;

import com.tdm.domain.model.idea.dto.SolutionIdea;

public class CurrentConsensusDTO implements CurrentConsensus {

	private double consensusLevel;
	private String problemId;
	private List<SolutionIdea> ranking;

	@Override
	public double getConsensusLevel() {
		return consensusLevel;
	}

	@Override
	public String getProblemId() {
		return problemId;
	}

	@Override
	public List<SolutionIdea> getRanking() {
		return ranking;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public void setRanking(List<SolutionIdea> ranking) {
		this.ranking = ranking;
	}
}
