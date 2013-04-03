package com.tdm.domain.model.preferences.internal;

import java.util.Collection;

import com.tdm.domain.model.preferences.MultiplicativePreferences;

public class MultiplicativePreferenceRelation extends
	PreferenceRelation<SolutionIdeaTupleWithMultiplicativeValue> implements
	MultiplicativePreferences {

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