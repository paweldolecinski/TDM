package com.tdm.server.application.decision;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation;
import com.tdm.server.application.decision.preference.SolutionIdeaTupleWithValue;
import com.tdm.server.application.decision.process.SelectionModule;

public class SelectionModuleTest {

	private SolutionIdeaId i1 = new SolutionIdeaId("1");
	private SolutionIdeaId i2 = new SolutionIdeaId("2");
	private SolutionIdeaId i3 = new SolutionIdeaId("3");
	private SolutionIdeaId i4 = new SolutionIdeaId("4");

	private SelectionModule selection = new SelectionModule();

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

		FuzzyPreferenceRelation collectivePreference = selection
				.getCollectivePreference(null,
						Arrays.asList(prefs1, prefs2, prefs3, prefs4));

		// Then
		SolutionIdeaTupleWithValue i1_i1 = collectivePreference.get(i1, i1);
		Assert.assertEquals("Incorect value for i1_i1", 0.5, i1_i1.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i1_i2 = collectivePreference.get(i1, i2);
		Assert.assertEquals("Incorect value for i1_i2", 0.52, i1_i2.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i2_i1", 0.48,
				i1_i2.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i3 = collectivePreference.get(i1, i3);
		Assert.assertEquals("Incorect value for i1_i3", 0.86, i1_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i1", 0.14,
				i1_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i4 = collectivePreference.get(i1, i4);
		Assert.assertEquals("Incorect value for i1_i4", 0.75, i1_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i1", 0.25,
				i1_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i2 = collectivePreference.get(i2, i2);
		Assert.assertEquals("Incorect value for i2_i2", 0.5, i2_i2.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i2_i3 = collectivePreference.get(i2, i3);
		Assert.assertEquals("Incorect value for i2_i3", 0.91, i2_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i2", 0.09,
				i2_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i4 = collectivePreference.get(i2, i4);
		Assert.assertEquals("Incorect value for i2_i4", 0.77, i2_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i2", 0.23,
				i2_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i3_i3 = collectivePreference.get(i3, i3);
		Assert.assertEquals("Incorect value for i3_i3", 0.5, i3_i3.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i3_i4 = collectivePreference.get(i3, i4);
		Assert.assertEquals("Incorect value for i3_i4", 0.44, i3_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i3", 0.56,
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
		List<SolutionIdeaId> globalRanking = selection.getGlobalRanking(ideas,
				collective);

		Assert.assertEquals(i2, globalRanking.get(0));
		Assert.assertEquals(i1, globalRanking.get(1));
		Assert.assertEquals(i4, globalRanking.get(2));
		Assert.assertEquals(i3, globalRanking.get(3));
	}
}
