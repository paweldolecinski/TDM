package com.tdm.server.logic.model;

public class GdmProblem {

	private final GdmProblemId id;
	private String name = "";
	private String description = "";

	public GdmProblem(GdmProblemId id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public GdmProblemId getId() {
		return id;
	}

}
