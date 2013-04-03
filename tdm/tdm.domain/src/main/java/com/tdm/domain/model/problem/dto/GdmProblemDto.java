package com.tdm.domain.model.problem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.GdmProblemCurrentConsensus;
import com.tdm.domain.model.problem.GdmProblemId;

public class GdmProblemDto implements GdmProblem {

    private GdmProblemId id;
    private String name;
    private String description;
    private Date creationDate;
    private List<Expert> experts = new ArrayList<>();
    private GdmProblemCurrentConsensus currentConsensus;

    public GdmProblemDto() {
	id = new GdmProblemIdDto(null);
    }

    @Override
    public void setName(String name) {
	this.name = name;
    }

    @Override
    public void setDescription(String description) {
	this.description = description;
    }

    public void setId(GdmProblemId id) {
	this.id = id;
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

    @Override
    public List<Expert> getExperts() {
	return experts;
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
