package com.tdm.server.logic.model;

import java.util.Date;

import pl.dolecinski.subdicium.server.datastore.dto.SolutionIdeaDTO;

public class SolutionIdeaImpl implements SolutionIdea {

	private SolutionIdeaId id;
	private GdmProblemId problemId;
	private String name;
	private String details;
	private Date creation;

	@Override
	public SolutionIdeaId getId() {
		return id;
	}

	@Override
	public GdmProblemId getProblemId() {
		return problemId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDetails() {
		return details;
	}

	@Override
	public Date getCreation() {
		return creation;
	}

	public void setId(SolutionIdeaId id) {
		this.id = id;
	}

	public void setProblemId(GdmProblemId problemId) {
		this.problemId = problemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static final SolutionIdea dtoToModel(SolutionIdeaDTO solutionIdeaDTO) {
		SolutionIdeaImpl solutionIdea = new SolutionIdeaImpl();
		long id = solutionIdeaDTO.getId();
		solutionIdea.setId(SolutionIdeaId.create(id));
		long problemId = solutionIdeaDTO.getProblemId();
		solutionIdea.setProblemId(GdmProblemId.create(problemId));
		solutionIdea.setName(solutionIdeaDTO.getName());
		return solutionIdea;
	}
}
