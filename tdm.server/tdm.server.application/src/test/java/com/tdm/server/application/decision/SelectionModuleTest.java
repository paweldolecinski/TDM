package com.tdm.server.application.decision;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation;
import com.tdm.server.application.decision.preference.SolutionIdeaNote;
import com.tdm.server.application.decision.preference.SolutionIdeaTupleWithValue;
import com.tdm.server.application.decision.preference.SolutionIdeaUtilityList;
import com.tdm.server.application.decision.preferences.UnifyingPreferences;
import com.tdm.server.application.decision.process.SelectionModule;

public class SelectionModuleTest {

	private SolutionIdeaId i1 = new SolutionIdeaId("1");
	private SolutionIdeaId i2 = new SolutionIdeaId("2");
	private SolutionIdeaId i3 = new SolutionIdeaId("3");
	private SolutionIdeaId i4 = new SolutionIdeaId("4");

	@Test
	public void shouldCalcualteCollectivePreference() {
		FuzzyPreferenceRelation prefs1 = new FuzzyPreferenceRelation();

		prefs1.add(new SolutionIdeaTupleWithValue(i1, i1, 0.5));
		prefs1.add(new SolutionIdeaTupleWithValue(i1, i2, 0.16));
		prefs1.add(new SolutionIdeaTupleWithValue(i1, i3, 0.33));
		prefs1.add(new SolutionIdeaTupleWithValue(i1, i4, 0.0));
		prefs1.add(new SolutionIdeaTupleWithValue(i2, i2, 0.5));
		prefs1.add(new SolutionIdeaTupleWithValue(i2, i3, 0.66));
		prefs1.add(new SolutionIdeaTupleWithValue(i2, i4, 0.33));
		prefs1.add(new SolutionIdeaTupleWithValue(i3, i3, 0.5));
		prefs1.add(new SolutionIdeaTupleWithValue(i3, i4, 0.16));
		prefs1.add(new SolutionIdeaTupleWithValue(i4, i4, 0.5));

		FuzzyPreferenceRelation prefs2 = new FuzzyPreferenceRelation();

		prefs2.add(new SolutionIdeaTupleWithValue(i1, i1, 0.5));
		prefs2.add(new SolutionIdeaTupleWithValue(i1, i2, 0.57));
		prefs2.add(new SolutionIdeaTupleWithValue(i1, i3, 0.88));
		prefs2.add(new SolutionIdeaTupleWithValue(i1, i4, 0.94));
		prefs2.add(new SolutionIdeaTupleWithValue(i2, i2, 0.5));
		prefs2.add(new SolutionIdeaTupleWithValue(i2, i3, 0.84));
		prefs2.add(new SolutionIdeaTupleWithValue(i2, i4, 0.92));
		prefs2.add(new SolutionIdeaTupleWithValue(i3, i3, 0.5));
		prefs2.add(new SolutionIdeaTupleWithValue(i3, i4, 0.69));
		prefs2.add(new SolutionIdeaTupleWithValue(i4, i4, 0.5));

		FuzzyPreferenceRelation prefs3 = new FuzzyPreferenceRelation();

		prefs3.add(new SolutionIdeaTupleWithValue(i1, i1, 0.5));
		prefs3.add(new SolutionIdeaTupleWithValue(i1, i2, 0.3));
		prefs3.add(new SolutionIdeaTupleWithValue(i1, i3, 0.9));
		prefs3.add(new SolutionIdeaTupleWithValue(i1, i4, 0.7));
		prefs3.add(new SolutionIdeaTupleWithValue(i2, i2, 0.5));
		prefs3.add(new SolutionIdeaTupleWithValue(i2, i3, 1.0));
		prefs3.add(new SolutionIdeaTupleWithValue(i2, i4, 0.8));
		prefs3.add(new SolutionIdeaTupleWithValue(i3, i3, 0.5));
		prefs3.add(new SolutionIdeaTupleWithValue(i3, i4, 0.2));
		prefs3.add(new SolutionIdeaTupleWithValue(i4, i4, 0.5));

		FuzzyPreferenceRelation prefs4 = new FuzzyPreferenceRelation();

		prefs4.add(new SolutionIdeaTupleWithValue(i1, i1, 0.5));
		prefs4.add(new SolutionIdeaTupleWithValue(i1, i2, 0.66));
		prefs4.add(new SolutionIdeaTupleWithValue(i1, i3, 0.97));
		prefs4.add(new SolutionIdeaTupleWithValue(i1, i4, 0.82));
		prefs4.add(new SolutionIdeaTupleWithValue(i2, i2, 0.5));
		prefs4.add(new SolutionIdeaTupleWithValue(i2, i3, 0.91));
		prefs4.add(new SolutionIdeaTupleWithValue(i2, i4, 0.66));
		prefs4.add(new SolutionIdeaTupleWithValue(i3, i3, 0.5));
		prefs4.add(new SolutionIdeaTupleWithValue(i3, i4, 0.18));
		prefs4.add(new SolutionIdeaTupleWithValue(i4, i4, 0.5));

		FuzzyPreferenceRelation collectivePreference = SelectionModule
				.getCollectivePreference(Arrays.asList(prefs1, prefs2, prefs3,
						prefs4));

		// Then
		SolutionIdeaTupleWithValue i1_i1 = collectivePreference.get(i1, i1);
		Assert.assertEquals("Incorect value for i1_i1", 0.5, i1_i1.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i1_i2 = collectivePreference.get(i1, i2);
		Assert.assertEquals("Incorect value for i1_i2", 0.33, i1_i2.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i2_i1", 0.66,
				i1_i2.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i3 = collectivePreference.get(i1, i3);
		Assert.assertEquals("Incorect value for i1_i3", 0.62, i1_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i1", 0.37,
				i1_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i4 = collectivePreference.get(i1, i4);
		Assert.assertEquals("Incorect value for i1_i4", 0.41, i1_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i1", 0.58,
				i1_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i2 = collectivePreference.get(i2, i2);
		Assert.assertEquals("Incorect value for i2_i2", 0.5, i2_i2.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i2_i3 = collectivePreference.get(i2, i3);
		Assert.assertEquals("Incorect value for i2_i3", 0.78, i2_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i2", 0.21,
				i2_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i4 = collectivePreference.get(i2, i4);
		Assert.assertEquals("Incorect value for i2_i4", 0.57, i2_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i2", 0.42,
				i2_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i3_i3 = collectivePreference.get(i3, i3);
		Assert.assertEquals("Incorect value for i3_i3", 0.5, i3_i3.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i3_i4 = collectivePreference.get(i3, i4);
		Assert.assertEquals("Incorect value for i3_i4", 0.27, i3_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i3", 0.72,
				i3_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i4_i4 = collectivePreference.get(i4, i4);
		Assert.assertEquals("Incorect value for i4_i4", 0.5, i4_i4.getValue(),
				0.01);
	}

	@Test
	public void shouldCalculateGlobalRanking() {
		FuzzyPreferenceRelation collective = new FuzzyPreferenceRelation();

		collective.add(new SolutionIdeaTupleWithValue(i1, i1, 0.5));
		collective.add(new SolutionIdeaTupleWithValue(i1, i2, 0.52));
		collective.add(new SolutionIdeaTupleWithValue(i1, i3, 0.86));
		collective.add(new SolutionIdeaTupleWithValue(i1, i4, 0.75));
		collective.add(new SolutionIdeaTupleWithValue(i2, i2, 0.5));
		collective.add(new SolutionIdeaTupleWithValue(i2, i3, 0.91));
		collective.add(new SolutionIdeaTupleWithValue(i2, i4, 0.77));
		collective.add(new SolutionIdeaTupleWithValue(i3, i3, 0.5));
		collective.add(new SolutionIdeaTupleWithValue(i3, i4, 0.44));
		collective.add(new SolutionIdeaTupleWithValue(i4, i4, 0.5));

		List<SolutionIdeaId> ideas = Arrays.asList(i1, i2, i3, i4);
		List<SolutionIdeaId> globalRanking = SelectionModule.getGlobalRanking(
				ideas, collective);

		Assert.assertEquals(i2, globalRanking.get(0));
		Assert.assertEquals(i1, globalRanking.get(1));
		Assert.assertEquals(i4, globalRanking.get(2));
		Assert.assertEquals(i3, globalRanking.get(3));
	}

	@Test
	public void test() {
		SolutionIdeaUtilityList preferences = new SolutionIdeaUtilityList();
		preferences.add(new SolutionIdeaNote(i1, 3));
		preferences.add(new SolutionIdeaNote(i2, 0));
		preferences.add(new SolutionIdeaNote(i3, 10));
		preferences.add(new SolutionIdeaNote(i4, 9));
		FuzzyPreferenceRelation fuzzy1 = UnifyingPreferences
				.transform(preferences);

		SolutionIdeaUtilityList preferences2 = new SolutionIdeaUtilityList();
		// preferences2.add(new SolutionIdeaNote(i1, 10));
		preferences2.add(new SolutionIdeaNote(i2, 9));
		preferences2.add(new SolutionIdeaNote(i3, 10));
		preferences2.add(new SolutionIdeaNote(i4, 9));
		FuzzyPreferenceRelation fuzzy2 = UnifyingPreferences
				.transform(preferences2);

		FuzzyPreferenceRelation collectivePreference = SelectionModule
				.getCollectivePreference(Arrays.asList(fuzzy1, fuzzy2));

		List<SolutionIdeaId> ideas = Arrays.asList(i1, i2, i3, i4);
		List<SolutionIdeaId> globalRanking = SelectionModule.getGlobalRanking(
				ideas, collectivePreference);

		Assert.assertEquals(i3, globalRanking.get(0));
		Assert.assertEquals(i4, globalRanking.get(1));
		Assert.assertEquals(i1, globalRanking.get(2));
		Assert.assertEquals(i2, globalRanking.get(3));
	}

	@Test
	public void testCalcProximity() {
		List<SolutionIdeaId> ideas = Arrays.asList(i1, i2, i3, i4);

		List<SolutionIdeaId> gloablRanking = Arrays.asList(i2, i1, i4, i3);
		List<SolutionIdeaId> expertRanking = Arrays.asList(i4, i2, i3, i1);
		double b = 0.7;
		for (SolutionIdeaId solutionIdeaId : ideas) {
			int vC = gloablRanking.indexOf(solutionIdeaId);
			int vK = expertRanking.indexOf(solutionIdeaId);

			double p = 0;
			int abs = Math.abs(vC - vK);
			if (ideas.size() - 1 > 0) {
				float i = (float) abs / (ideas.size() - 1);
				p = Math.pow(i, b);
			}
			System.out.println(solutionIdeaId + " = " + p);
		}
		System.out.println();
		expertRanking = Arrays.asList(i1, i2, i3, i4);

		for (SolutionIdeaId solutionIdeaId : ideas) {
			int vC = gloablRanking.indexOf(solutionIdeaId);
			int vK = expertRanking.indexOf(solutionIdeaId);
			double p = 0;
			int abs = Math.abs(vC - vK);
			if (ideas.size() - 1 > 0) {
				float i = (float) abs / (ideas.size() - 1);
				p = Math.pow(i, b);
			}
			System.out.println(solutionIdeaId + " = " + p);
		}
		System.out.println();
		expertRanking = Arrays.asList(i1, i2, i4, i3);

		for (SolutionIdeaId solutionIdeaId : ideas) {
			int vC = gloablRanking.indexOf(solutionIdeaId);
			int vK = expertRanking.indexOf(solutionIdeaId);
			double p = 0;
			int abs = Math.abs(vC - vK);
			if (ideas.size() - 1 > 0) {
				float i = (float) abs / (ideas.size() - 1);
				p = Math.pow(i, b);
			}
			System.out.println(solutionIdeaId + " = " + p);
		}
		System.out.println();
		expertRanking = Arrays.asList(i2, i1, i4, i3);

		for (SolutionIdeaId solutionIdeaId : ideas) {
			int vC = gloablRanking.indexOf(solutionIdeaId);
			int vK = expertRanking.indexOf(solutionIdeaId);
			double p = 0;
			int abs = Math.abs(vC - vK);
			if (ideas.size() - 1 > 0) {
				float i = (float) abs / (ideas.size() - 1);
				p = Math.pow(i, b);
			}
			System.out.println(solutionIdeaId + " = " + p);
		}

	}

	@Test
	public void calcConsensus() {
		ListMultimap<SolutionIdeaId, Double> multimap = ArrayListMultimap
				.create();
		multimap.put(i1, 0.77);
		multimap.put(i1, 0.46);
		multimap.put(i1, 0.46);
		multimap.put(i1, 0.0);

		multimap.put(i2, 0.46);
		multimap.put(i2, 0.46);
		multimap.put(i2, 0.46);
		multimap.put(i2, 0.0);

		multimap.put(i3, 0.46);
		multimap.put(i3, 0.46);
		multimap.put(i3, 0.0);
		multimap.put(i3, 0.0);

		multimap.put(i4, 0.77);
		multimap.put(i4, 0.46);
		multimap.put(i4, 0.0);
		multimap.put(i4, 0.0);

		float cX = 0;
		Set<SolutionIdeaId> keySet = multimap.keySet();
		for (SolutionIdeaId x : keySet) {
			List<Double> p = multimap.get(x);
			double c = 0;
			for (Double pk : p) {
				c += pk / p.size();
			}
			c = 1 - c;
			System.out.println("C(" + x + ") = " + c);
			cX += (float) c / keySet.size();
		}
		// cX *= 0.;
		System.out.println("cX = " + Math.round(cX * 100));
	}
}
