package com.tdm.domain.model.problem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tdm.domain.model.expert.dto.Expert;

public class ProblemDTO implements Problem {

	private String key;
	private String name;
	private String description;
	private Date creationDate;
	private List<Expert> experts = new ArrayList<Expert>();
	private GdmProblemCurrentConsensus currentConsensus;

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
	public List<Expert> getExperts() {
		return experts;
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
	public void addExpert(Expert expert) {
		experts.add(expert);
	}

	@Override
	public GdmProblemCurrentConsensus getCurrentConsensus() {
		return currentConsensus;
	}

	public void setCurrentConsensus(GdmProblemCurrentConsensus currentConsensus) {
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
