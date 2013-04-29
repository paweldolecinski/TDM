package com.tdm.domain.model.problem.dto;

import java.util.Date;

public class ProblemDTO implements Problem {

	private String key;
	private String name;
	private String description;
	private Date creationDate;
	private CurrentConsensus currentConsensus;

	@Override
	public String getKey() {
		return key;
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
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public CurrentConsensus getCurrentConsensus() {
		return currentConsensus;
	}

	public void setCurrentConsensus(CurrentConsensus currentConsensus) {
		this.currentConsensus = currentConsensus;
	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
