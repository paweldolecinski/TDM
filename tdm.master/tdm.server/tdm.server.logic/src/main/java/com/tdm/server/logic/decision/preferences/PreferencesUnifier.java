package com.tdm.server.logic.decision.preferences;

import java.util.HashMap;
import java.util.Map.Entry;

import com.tdm.server.logic.model.FuzzyPreferences;
import com.tdm.server.logic.model.MultiplicativePreferences;
import com.tdm.server.logic.model.SolutionIdeaNote;
import com.tdm.server.logic.model.SolutionIdeaOrderList;
import com.tdm.server.logic.model.SolutionIdeaTuple;
import com.tdm.server.logic.model.SolutionIdeaUtilityList;

public class PreferencesUnifier {

	private final double logBase = Math.log(9);

	public FuzzyPreferences transform(SolutionIdeaOrderList preferences) {
		HashMap<SolutionIdeaTuple, Double> fuzzyMap = new HashMap<SolutionIdeaTuple, Double>();

		int n = preferences.size();
		for (SolutionIdeaNote o_i : preferences) {
			for (SolutionIdeaNote o_j : preferences) {
				double pref = (1 + (o_i.getNote() + o_j.getNote()) / (n - 1)) / 2;
				fuzzyMap.put(
						new SolutionIdeaTuple(o_i.getSolutionId(), o_j
								.getSolutionId()), pref);
			}
		}
		return new FuzzyPreferences(fuzzyMap);
	}

	public FuzzyPreferences transform(SolutionIdeaUtilityList preferences) {
		HashMap<SolutionIdeaTuple, Double> fuzzyMap = new HashMap<SolutionIdeaTuple, Double>();

		for (SolutionIdeaNote u_i : preferences) {
			for (SolutionIdeaNote u_j : preferences) {
				double pref = (Math.sqrt(u_i.getNote()))
						/ (Math.sqrt(u_i.getNote()) + Math.sqrt(u_j.getNote()));
				fuzzyMap.put(
						new SolutionIdeaTuple(u_i.getSolutionId(), u_j
								.getSolutionId()), pref);
			}
		}
		return new FuzzyPreferences(fuzzyMap);
	}

	public FuzzyPreferences transform(MultiplicativePreferences preferences) {
		HashMap<SolutionIdeaTuple, Double> fuzzyMap = new HashMap<SolutionIdeaTuple, Double>();

		for (Entry<SolutionIdeaTuple, Double> entry : preferences.entrySet()) {
			double pref = (1 + log(entry.getValue())) / 2;
			fuzzyMap.put(entry.getKey(), pref);
		}
		return new FuzzyPreferences(fuzzyMap);
	}

	private double log(double num) {
		return Math.log(num) / logBase;
	}
}
