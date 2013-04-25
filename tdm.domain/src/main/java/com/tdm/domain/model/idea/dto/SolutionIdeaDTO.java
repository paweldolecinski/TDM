package com.tdm.domain.model.idea.dto;

import java.util.Date;

public class SolutionIdeaDTO implements SolutionIdea {

	private String id;
	private String problemId;
	private String name;
	private String details;
	private Date creationDate;

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
	public Date getCreationDate() {
		return creationDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setCreationDate(Date creation) {
		this.creationDate = creation;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
}
