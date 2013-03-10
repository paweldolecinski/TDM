package com.tdm.server.logic.model;

import java.util.Collection;

public class FuzzyPreferenceRelation extends
		PreferenceRelation<SolutionIdeaTupleWithValue> {

	private static final long serialVersionUID = -8119377108595639064L;

	public FuzzyPreferenceRelation() {
		super();
	}

	public FuzzyPreferenceRelation(Collection<SolutionIdeaTupleWithValue> m) {
		super(m);
	}

	@Override
	protected SolutionIdeaTupleWithValue reverseTuple(
			SolutionIdeaTupleWithValue tuple) {
		return new SolutionIdeaTupleWithValue(tuple.getRight(),
				tuple.getLeft(), tuple.getReverseValue());
	}

}
