package com.tdm.server.logic.model;

import java.util.Collection;

public class MultiplicativePreferenceRelation extends
		PreferenceRelation<SolutionIdeaTupleWithMultiplicativeValue> {

	private static final long serialVersionUID = -4068568723486944561L;

	public MultiplicativePreferenceRelation() {
		super();
	}

	public MultiplicativePreferenceRelation(
			Collection<SolutionIdeaTupleWithMultiplicativeValue> m) {
		super(m);
	}

	@Override
	protected SolutionIdeaTupleWithMultiplicativeValue reverseTuple(
			SolutionIdeaTupleWithMultiplicativeValue tuple) {
		return new SolutionIdeaTupleWithMultiplicativeValue(tuple.getRight(),
				tuple.getLeft(), tuple.getReverseValue());
	}

}
