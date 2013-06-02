package com.tdm.domain.model.preferences;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class IdeaPairPreferences implements Serializable {

	private static final long serialVersionUID = 1465422248651521190L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Key leftSolution;
	@Persistent
	private Key rightSolution;
	@Persistent
	private double value;

	public IdeaPairPreferences() {
	}

	public IdeaPairPreferences(String leftSolutionId, String rightSolutionId,
			double value) {
		this.leftSolution = KeyFactory.stringToKey(leftSolutionId);
		this.rightSolution = KeyFactory.stringToKey(rightSolutionId);
		this.value = value;
	}

	public Key getLeftSolution() {
		return leftSolution;
	}

	public void setLeftSolution(Key leftSolution) {
		this.leftSolution = leftSolution;
	}

	public Key getRightSolution() {
		return rightSolution;
	}

	public void setRightSolution(Key rightSolution) {
		this.rightSolution = rightSolution;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((leftSolution == null) ? 0 : leftSolution.hashCode());
		result = prime * result
				+ ((rightSolution == null) ? 0 : rightSolution.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdeaPairPreferences other = (IdeaPairPreferences) obj;
		if (leftSolution == null) {
			if (other.leftSolution != null)
				return false;
		} else if (!leftSolution.equals(other.leftSolution))
			return false;
		if (rightSolution == null) {
			if (other.rightSolution != null)
				return false;
		} else if (!rightSolution.equals(other.rightSolution))
			return false;
		return true;
	}

}
