package com.tdm.server.logic.model;

public class GdmProblemId {

	public static final GdmProblemId EMPTY_ID = new GdmProblemId(-1);
	
	private long id;

	public static GdmProblemId create(long id) {
		return new GdmProblemId(id);
	}

	private GdmProblemId(long id) {
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
		GdmProblemId other = (GdmProblemId) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
