package com.tdm.server.logic.model;

import com.tdm.common.dto.SolutionIdeaId;

public class SolutionIdeaNote {

	private SolutionIdeaId solutionId;

	private int utility;

	public SolutionIdeaNote(SolutionIdeaId solutionId, int utility) {
		this.solutionId = solutionId;
		this.utility = utility;
	}

	public SolutionIdeaId getSolutionId() {
		return solutionId;
	}

	public int getNote() {
		return utility;
	}

	@Override
	public String toString() {
		return solutionId + " -> " + utility;
	}

}
