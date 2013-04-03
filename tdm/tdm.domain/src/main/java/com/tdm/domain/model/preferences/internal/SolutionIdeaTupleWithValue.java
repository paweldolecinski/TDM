package com.tdm.domain.model.preferences.internal;

import com.tdm.domain.model.idea.SolutionIdeaId;

public class SolutionIdeaTupleWithValue extends SolutionIdeaTuple {

	private double value;

	public SolutionIdeaTupleWithValue(SolutionIdeaId left,
			SolutionIdeaId right, double value) {
		super(left, right);
		this.value = value;
	}

	public SolutionIdeaTupleWithValue(SolutionIdeaTuple tuple, double value) {
		this(tuple.getLeft(), tuple.getRight(), value);
	}

	public double getValue() {
		return value;
	}

	public double getReverseValue() {
		return 1 - getValue();
	}

	@Override
	public String toString() {
		return "[" + getLeft() + ", " + getRight() + "] = " + value;
	}
}
