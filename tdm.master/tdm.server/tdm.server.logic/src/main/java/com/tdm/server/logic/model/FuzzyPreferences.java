package com.tdm.server.logic.model;

import java.util.HashMap;
import java.util.Map;

public class FuzzyPreferences extends HashMap<SolutionIdeaTuple, Double> {

	private static final long serialVersionUID = -2879591976656327928L;

	public FuzzyPreferences() {
		super();
	}

	public FuzzyPreferences(Map<? extends SolutionIdeaTuple, ? extends Double> m) {
		super(m);
	}

}
