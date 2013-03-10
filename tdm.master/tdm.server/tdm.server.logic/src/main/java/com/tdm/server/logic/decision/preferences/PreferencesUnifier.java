package com.tdm.server.logic.decision.preferences;

import java.util.ArrayList;
import java.util.List;

import com.tdm.server.logic.model.FuzzyPreferenceRelation;
import com.tdm.server.logic.model.MultiplicativePreferenceRelation;
import com.tdm.server.logic.model.PreferenceRelation;
import com.tdm.server.logic.model.SolutionIdeaNote;
import com.tdm.server.logic.model.SolutionIdeaOrderList;
import com.tdm.server.logic.model.SolutionIdeaTupleWithValue;
import com.tdm.server.logic.model.SolutionIdeaUtilityList;

public class PreferencesUnifier {

	private final double logBase = Math.log(9);

	/**
	 * @param preferences
	 * @return
	 */
	public PreferenceRelation transform(SolutionIdeaOrderList preferences) {
		List<SolutionIdeaTupleWithValue> fuzzyList = new ArrayList<SolutionIdeaTupleWithValue>();

		double n = preferences.size() - 1;
		for (int i = 0; i < preferences.size(); i++) {
			SolutionIdeaNote o_i = preferences.get(i);
			for (int j = i; j < preferences.size(); j++) {
				SolutionIdeaNote o_j = preferences.get(j);
				double l = o_j.getNote() - o_i.getNote();
				double pref = (1 + (l / n)) / 2;
				fuzzyList.add(new SolutionIdeaTupleWithValue(o_i.getSolutionId(), o_j
						.getSolutionId(), pref));
			}
		}
		return new FuzzyPreferenceRelation(fuzzyList);
	}

	public PreferenceRelation transform(SolutionIdeaUtilityList preferences) {
		List<SolutionIdeaTupleWithValue> fuzzyList = new ArrayList<SolutionIdeaTupleWithValue>();

		for (int i = 0; i < preferences.size(); i++) {
			SolutionIdeaNote u_i = preferences.get(i);
			for (int j = i; j < preferences.size(); j++) {
				SolutionIdeaNote u_j = preferences.get(j);
				double pref = (Math.pow(u_i.getNote(), 2))
						/ (Math.pow(u_i.getNote(), 2) + Math.pow(u_j.getNote(),
								2));
				fuzzyList.add(new SolutionIdeaTupleWithValue(u_i.getSolutionId(), u_j
						.getSolutionId(), pref));
			}
		}
		return new FuzzyPreferenceRelation(fuzzyList);
	}

	public PreferenceRelation transform(
			MultiplicativePreferenceRelation preferences) {
		List<SolutionIdeaTupleWithValue> fuzzyList = new ArrayList<SolutionIdeaTupleWithValue>();

		for (SolutionIdeaTupleWithValue tuple : preferences) {
			double pref = (1 + log(tuple.getValue())) / 2;
			fuzzyList.add(new SolutionIdeaTupleWithValue(tuple, pref));
		}
		return new FuzzyPreferenceRelation(fuzzyList);
	}

	private double log(double num) {
		return Math.log(num) / logBase;
	}
}
