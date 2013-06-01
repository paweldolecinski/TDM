package com.tdm.domain.model.problem.dto;

import java.util.List;

import com.tdm.domain.model.idea.dto.SolutionIdea;
import com.tdm.gwt.client.BaseJso;

public class CurrentConsensusJSO extends BaseJso implements CurrentConsensus {

	protected CurrentConsensusJSO() {
	}

	@Override
	public final native double getConsensusLevel()/*-{
		return this.consensusLevel;
	}-*/;


	@Override
	public final native String getProblemId()/*-{
		return this.problemId;
	}-*/;


	@Override
	public final native List<SolutionIdea> getRanking()/*-{
		return this.ranking;
	}-*/;

}
