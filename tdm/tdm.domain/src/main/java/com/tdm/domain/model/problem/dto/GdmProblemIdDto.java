package com.tdm.domain.model.problem.dto;

import com.tdm.domain.model.problem.GdmProblemId;

public class GdmProblemIdDto implements GdmProblemId {

	private String id;

	public GdmProblemIdDto(String id) {
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

		GdmProblemIdDto other = (GdmProblemIdDto) obj;
		return sameValueAs(other);
	}

	boolean sameValueAs(GdmProblemIdDto other) {
		return other != null && this.id.equals(other.id);
	}

	@Override
	public String toString() {
		return id;
	}
}
