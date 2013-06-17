package com.tdm.server.application.decision.preferences;

import org.junit.Assert;
import org.junit.Test;

import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation;
import com.tdm.server.application.decision.preference.MultiplicativePreferenceRelation;
import com.tdm.server.application.decision.preference.SolutionIdeaNote;
import com.tdm.server.application.decision.preference.SolutionIdeaOrderList;
import com.tdm.server.application.decision.preference.SolutionIdeaTupleWithMultiplicativeValue;
import com.tdm.server.application.decision.preference.SolutionIdeaTupleWithValue;
import com.tdm.server.application.decision.preference.SolutionIdeaUtilityList;

public class PreferencesUnifierTest {

	private SolutionIdeaId i1 = new SolutionIdeaId("1");
	private SolutionIdeaId i2 = new SolutionIdeaId("2");
	private SolutionIdeaId i3 = new SolutionIdeaId("3");
	private SolutionIdeaId i4 = new SolutionIdeaId("4");

	@Test
	public void shouldTransformOrderToFuzzyRel() {
		// Given
		SolutionIdeaOrderList solutionIdeaOrderList = new SolutionIdeaOrderList();

		solutionIdeaOrderList.add(new SolutionIdeaNote(i1, 4));
		solutionIdeaOrderList.add(new SolutionIdeaNote(i2, 2));
		solutionIdeaOrderList.add(new SolutionIdeaNote(i3, 3));
		solutionIdeaOrderList.add(new SolutionIdeaNote(i4, 1));

		// When
		FuzzyPreferenceRelation transform = UnifyingPreferences
				.transform(solutionIdeaOrderList);

		// Then
		SolutionIdeaTupleWithValue i1_i1 = transform.get(i1, i1);
		Assert.assertEquals("Incorect value for i1_i1", 0.5, i1_i1.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i1_i2 = transform.get(i1, i2);
		Assert.assertEquals("Incorect value for i1_i2", 0.16, i1_i2.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i2_i1", 0.83,
				i1_i2.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i3 = transform.get(i1, i3);
		Assert.assertEquals("Incorect value for i1_i3", 0.33, i1_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i1", 0.66,
				i1_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i4 = transform.get(i1, i4);
		Assert.assertEquals("Incorect value for i1_i4", 0.0, i1_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i1", 1.0,
				i1_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i2 = transform.get(i2, i2);
		Assert.assertEquals("Incorect value for i2_i2", 0.5, i2_i2.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i2_i3 = transform.get(i2, i3);
		Assert.assertEquals("Incorect value for i2_i3", 0.66, i2_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i2", 0.33,
				i2_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i4 = transform.get(i2, i4);
		Assert.assertEquals("Incorect value for i2_i4", 0.33, i2_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i2", 0.66,
				i2_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i3_i3 = transform.get(i3, i3);
		Assert.assertEquals("Incorect value for i3_i3", 0.5, i3_i3.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i3_i4 = transform.get(i3, i4);
		Assert.assertEquals("Incorect value for i3_i4", 0.16, i3_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i3", 0.83,
				i3_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i4_i4 = transform.get(i4, i4);
		Assert.assertEquals("Incorect value for i4_i4", 0.5, i4_i4.getValue(),
				0.01);
	}

	@Test
	public void shouldTransformUtilityToFuzzyRel() {
		// Given
		SolutionIdeaUtilityList preferences = new SolutionIdeaUtilityList();

		preferences.add(new SolutionIdeaNote(i1, 8));
		preferences.add(new SolutionIdeaNote(i2, 7));
		preferences.add(new SolutionIdeaNote(i3, 3));
		preferences.add(new SolutionIdeaNote(i4, 2));

		// When
		FuzzyPreferenceRelation transform = UnifyingPreferences
				.transform(preferences);

		// Then
		SolutionIdeaTupleWithValue i1_i1 = transform.get(i1, i1);
		Assert.assertEquals("Incorect value for i1_i1", 0.5, i1_i1.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i1_i2 = transform.get(i1, i2);
		Assert.assertEquals("Incorect value for i1_i2", 0.57, i1_i2.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i2_i1", 0.43,
				i1_i2.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i3 = transform.get(i1, i3);
		Assert.assertEquals("Incorect value for i1_i3", 0.88, i1_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i1", 0.12,
				i1_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i4 = transform.get(i1, i4);
		Assert.assertEquals("Incorect value for i1_i4", 0.94, i1_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i1", 0.06,
				i1_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i2 = transform.get(i2, i2);
		Assert.assertEquals("Incorect value for i2_i2", 0.5, i2_i2.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i2_i3 = transform.get(i2, i3);
		Assert.assertEquals("Incorect value for i2_i3", 0.84, i2_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i2", 0.16,
				i2_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i4 = transform.get(i2, i4);
		Assert.assertEquals("Incorect value for i2_i4", 0.92, i2_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i2", 0.08,
				i2_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i3_i3 = transform.get(i3, i3);
		Assert.assertEquals("Incorect value for i3_i3", 0.5, i3_i3.getValue(),
				0.01);

		SolutionIdeaTupleWithValue i3_i4 = transform.get(i3, i4);
		Assert.assertEquals("Incorect value for i3_i4", 0.69, i3_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i3", 0.31,
				i3_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i4_i4 = transform.get(i4, i4);
		Assert.assertEquals("Incorect value for i4_i4", 0.5, i4_i4.getValue(),
				0.01);

	}

	@Test
	public void shouldTransformMultiplicativeToFuzzyRel() {
		// Given
		MultiplicativePreferenceRelation preferences = new MultiplicativePreferenceRelation();

		preferences
				.add(new SolutionIdeaTupleWithMultiplicativeValue(i1, i2, 2));
		preferences
				.add(new SolutionIdeaTupleWithMultiplicativeValue(i1, i3, 8));
		preferences
				.add(new SolutionIdeaTupleWithMultiplicativeValue(i1, i4, 4));
		preferences
				.add(new SolutionIdeaTupleWithMultiplicativeValue(i2, i3, 6));
		preferences
				.add(new SolutionIdeaTupleWithMultiplicativeValue(i2, i4, 2));
		preferences
				.add(new SolutionIdeaTupleWithMultiplicativeValue(i4, i3, 4));

		// When
		FuzzyPreferenceRelation transform = UnifyingPreferences
				.transform(preferences);

		// Then

		SolutionIdeaTupleWithValue i1_i2 = transform.get(i1, i2);
		Assert.assertEquals("Incorect value for i1_i2", 0.66, i1_i2.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i2_i1", 0.34,
				i1_i2.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i3 = transform.get(i1, i3);
		Assert.assertEquals("Incorect value for i1_i3", 0.97, i1_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i1", 0.03,
				i1_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i1_i4 = transform.get(i1, i4);
		Assert.assertEquals("Incorect value for i1_i4", 0.82, i1_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i1", 0.18,
				i1_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i3 = transform.get(i2, i3);
		Assert.assertEquals("Incorect value for i2_i3", 0.91, i2_i3.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i3_i2", 0.09,
				i2_i3.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i2_i4 = transform.get(i2, i4);
		Assert.assertEquals("Incorect value for i2_i4", 0.66, i2_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i2", 0.34,
				i2_i4.getReverseValue(), 0.01);

		SolutionIdeaTupleWithValue i3_i4 = transform.get(i3, i4);
		Assert.assertEquals("Incorect value for i3_i4", 0.18, i3_i4.getValue(),
				0.01);
		Assert.assertEquals("Incorect value for i4_i3", 0.82,
				i3_i4.getReverseValue(), 0.01);
	}
}