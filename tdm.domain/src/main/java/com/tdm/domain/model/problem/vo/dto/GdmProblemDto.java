package com.tdm.domain.model.problem.vo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tdm.domain.model.expert.vo.Expert;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.GdmProblemCurrentConsensus;
import com.tdm.domain.model.problem.vo.GdmProblemKey;

public class GdmProblemDto implements GdmProblem {

	private GdmProblemKey key;
	private String name;
	private String description;
	private Date creationDate;
	private List<Expert> experts = new ArrayList<>();
	private GdmProblemCurrentConsensus currentConsensus;

	public GdmProblemDto() {
		key = new GdmProblemKeyDto(null);
	}

	@Override
	public GdmProblemKey getKey() {
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

	public void setGdmProblemKey(GdmProblemKey GdmProblemKey) {
		this.key = GdmProblemKey;
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
