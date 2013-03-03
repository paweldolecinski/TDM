package com.tdm.server.logic.model;

public class SolutionIdeaNote {

	private SolutionIdeaId solutionId;

	private int utility;

	public SolutionIdeaId getSolutionId() {
		return solutionId;
	}

	public int getNote() {
		return utility;
	}

	public void setSolutionId(SolutionIdeaId solutionId) {
		this.solutionId = solutionId;
	}

	public void setUtility(int utility) {
		this.utility = utility;
	}
}
