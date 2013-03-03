package com.tdm.server.logic.model;

import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;


public class GdmProblemImpl implements GdmProblem {

	private final GdmProblemId id;
	private String name = "";
	private String description = "";

	public GdmProblemImpl(GdmProblemId id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public GdmProblemId getId() {
		return id;
	}

	public static final GdmProblem dtoToModel(ProblemDTO problem) {
		long id = problem.getId();
		GdmProblem gdmProblem = new GdmProblemImpl(GdmProblemId.create(id));
		gdmProblem.setName(problem.getName());
		gdmProblem.setDescription(problem.getDescription());
		return gdmProblem;
	}
}
