package com.tdm.domain.model.idea;

public class SolutionIdeaId {

	private String id;

	public SolutionIdeaId(String id) {
		this.id = id;
	}

	public String getIdString() {
		return id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
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
		return id;
	}
}
