package com.tdm.domain.model.problem.dto;

import java.util.List;

import com.tdm.domain.model.idea.dto.SolutionIdea;

public interface CurrentConsensus {

	int getConsensusLevel();

	String getProblemId();

	List<SolutionIdea> getRanking();

}