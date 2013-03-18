package com.tdm.server.logic.model;

public class SolutionIdeaId {

	private long id;

	public static SolutionIdeaId create(long id) {
		return new SolutionIdeaId(id);
	}

	private SolutionIdeaId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		SolutionIdeaId other = (SolutionIdeaId) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + id;
	}
}
