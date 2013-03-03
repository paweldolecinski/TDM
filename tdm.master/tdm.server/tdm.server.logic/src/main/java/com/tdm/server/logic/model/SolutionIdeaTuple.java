package com.tdm.server.logic.model;

public class SolutionIdeaTuple {

	private SolutionIdeaId first;

	private SolutionIdeaId second;

	public SolutionIdeaTuple(SolutionIdeaId first, SolutionIdeaId second) {
		super();
		this.first = first;
		this.second = second;
	}

	public SolutionIdeaId getFirst() {
		return first;
	}

	public SolutionIdeaId getSecond() {
		return second;
	}
}
