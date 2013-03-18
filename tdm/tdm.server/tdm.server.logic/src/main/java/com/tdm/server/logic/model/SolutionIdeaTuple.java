package com.tdm.server.logic.model;

public class SolutionIdeaTuple {

	private SolutionIdeaId left;
	private SolutionIdeaId right;

	public SolutionIdeaTuple(SolutionIdeaId left, SolutionIdeaId right) {
		this.left = left;
		this.right = right;
	}

	public SolutionIdeaId getLeft() {
		return left;
	}

	public SolutionIdeaId getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		SolutionIdeaTuple other = (SolutionIdeaTuple) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + left + ", " + right + "]";
	}

}