package com.tdm.server.logic.model;

public class SolutionIdeaTupleWithMultiplicativeValue extends
		SolutionIdeaTupleWithValue {

	public SolutionIdeaTupleWithMultiplicativeValue(SolutionIdeaId left,
			SolutionIdeaId right, double value) {
		super(left, right, value);
	}

	public SolutionIdeaTupleWithMultiplicativeValue(SolutionIdeaTuple tuple,
			double value) {
		super(tuple, value);
	}

	@Override
	public double getReverseValue() {
		return 1 / getValue();
	}

}
