package com.tdm.domain.model.problem.vo.dto;

import com.tdm.domain.model.problem.vo.GdmProblemKey;

public class GdmProblemKeyDto implements GdmProblemKey {

	private String id;

	public GdmProblemKeyDto(String id) {
		this.id = id;
	}

	public String getId() {
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

		GdmProblemKeyDto other = (GdmProblemKeyDto) obj;
		return sameValueAs(other);
	}

	boolean sameValueAs(GdmProblemKeyDto other) {
		return other != null && this.id.equals(other.id);
	}

	@Override
	public String toString() {
		return id;
	}
}
