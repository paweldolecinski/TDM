package com.tdm.server.logic.model;

import java.util.HashMap;
import java.util.Map;

public class MultiplicativePreferences extends
		HashMap<SolutionIdeaTuple, Double> {

	private static final long serialVersionUID = -4068568723486944561L;

	public MultiplicativePreferences() {
		super();
	}

	public MultiplicativePreferences(
			Map<? extends SolutionIdeaTuple, ? extends Double> m) {
		super(m);
	}

}
