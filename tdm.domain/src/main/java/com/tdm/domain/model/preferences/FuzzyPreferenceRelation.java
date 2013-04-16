package com.tdm.domain.model.preferences;

import java.util.Collection;

import com.tdm.domain.model.preferences.vo.FuzzyPreferences;

public class FuzzyPreferenceRelation extends
	PreferenceRelation<SolutionIdeaTupleWithValue> implements
	FuzzyPreferences {

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
