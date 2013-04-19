package com.tdm.domain.model.idea.dto;

import java.util.Date;


public class SolutionIdeaDTO implements SolutionIdea {

	private String id;
	private String problemId;
	private String name;
	private String details;
	private Date creation;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getProblemId() {
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

	public void setId(String id) {
		this.id = id;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public void setName(String name) {
		this.name = name;
	}

}
