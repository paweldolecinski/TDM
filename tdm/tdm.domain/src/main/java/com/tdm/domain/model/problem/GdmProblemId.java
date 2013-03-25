package com.tdm.domain.model.problem;

public class GdmProblemId {

	private String id;

	public GdmProblemId(String id) {
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
		if (obj == null || getClass() != obj.getClass())
			return false;

		GdmProblemId other = (GdmProblemId) obj;
		return sameValueAs(other);
	}

	boolean sameValueAs(GdmProblemId other) {
		return other != null && this.id.equals(other.id);
	}

	@Override
	public String toString() {
		return id;
	}
}
