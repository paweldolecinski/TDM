package com.tdm.common.dto;

public class ExpertId {
	public static final ExpertId EMPTY_ID = new ExpertId(-1);

	private long id;

	public static ExpertId create(long id) {
		return new ExpertId(id);
	}

	private ExpertId(long id) {
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
		ExpertId other = (ExpertId) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
