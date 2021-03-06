package com.tdm.server.application.decision.preference;

import java.util.ArrayList;
import java.util.Collection;

import com.tdm.domain.model.idea.SolutionIdeaId;

public abstract class PreferenceRelation<T extends SolutionIdeaTupleWithValue>
		extends ArrayList<T> {

	private static final long serialVersionUID = -2879591976656327928L;

	public PreferenceRelation() {
		super();
	}

	public PreferenceRelation(Collection<T> c) {
		super();
		for (T t : c) {
			add(t);
		}
	}

	@Override
	public boolean add(T e) {
		if (e.getLeft().getIdString()
				.compareToIgnoreCase(e.getRight().getIdString()) < 0) {
			return super.add(e);
		} else {
			return super.add(reverseTuple(e));
		}

	}

	abstract protected T reverseTuple(T tuple);

	public SolutionIdeaTupleWithValue get(T tuple) {
		return get(tuple.getLeft(), tuple.getRight());
	}

	public T get(SolutionIdeaId i1, SolutionIdeaId i2) {
		int indexOf = -1;
		if (i1.getIdString().compareToIgnoreCase(i2.getIdString()) < 0) {
			indexOf = indexOf(new SolutionIdeaTuple(i1, i2));
		} else {
			indexOf = indexOf(new SolutionIdeaTuple(i2, i1));
		}
		if(indexOf == -1)
		{
			return null;
		}
		return get(indexOf);
	}

	public double getValue(SolutionIdeaId i1, SolutionIdeaId i2) {
		T tuple = get(i1, i2);
		if (i1.getIdString().compareToIgnoreCase(i2.getIdString()) < 0) {
			return tuple.getValue();
		}
		return tuple.getReverseValue();
	}
}