package com.tdm.domain.model.idea.dto;

import java.util.Date;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.problem.GdmProblemId;

public class SolutionIdeaDto implements SolutionIdea {

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

}
