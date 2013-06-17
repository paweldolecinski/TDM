package com.tdm.domain.model.problem.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.tdm.domain.model.idea.dto.SolutionIdea;
import com.tdm.domain.model.idea.dto.SolutionIdeaJSO;
import com.tdm.gwt.client.BaseJso;

public class CurrentConsensusJSO extends BaseJso implements CurrentConsensus {

	protected CurrentConsensusJSO() {
	}

	@Override
	public final native int getConsensusLevel()/*-{
													return this.consensusLevel;
													}-*/;

	@Override
	public final native String getProblemId()/*-{
												return this.problemId;
												}-*/;

	@Override
	public final List<SolutionIdea> getRanking() {
		ArrayList<SolutionIdea> res = new ArrayList<SolutionIdea>();
		JsArray<SolutionIdeaJSO> nativeRanking = getNativeRanking();
		for (int i = 0; i < nativeRanking.length(); i++) {
			res.add(nativeRanking.get(i));
		}
		return res;
	}

	private final native JsArray<SolutionIdeaJSO> getNativeRanking()/*-{
																	return this.ranking;
																	}-*/;
}
