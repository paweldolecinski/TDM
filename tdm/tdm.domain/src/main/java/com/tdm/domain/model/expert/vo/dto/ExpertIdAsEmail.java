package com.tdm.domain.model.expert.vo.dto;

import com.tdm.domain.model.expert.vo.ExpertId;

public class ExpertIdAsEmail implements ExpertId {
	private String id;

	public ExpertIdAsEmail(String id) {
		this.id = id;
	}

	@Override
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
		if (obj == null || !(ExpertId.class.isAssignableFrom(obj.getClass())))
			return false;

		ExpertId other = (ExpertId) obj;
		return sameValueAs(other);
	}

	boolean sameValueAs(ExpertId other) {
		return other != null && this.id.equals(other.getIdString());
	}

	@Override
	public String toString() {
		return id;
	}
}
