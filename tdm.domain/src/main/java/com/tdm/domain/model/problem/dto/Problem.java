package com.tdm.domain.model.problem.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tdm.domain.model.expert.dto.Expert;

public interface Problem {

	String getKey();

	@NotNull(message = "Title cannot be empty.")
	@Size(min = 1, message = "Title must be at least 1 character long.")
	String getName();

	String getDescription();

	Date getCreationDate();

	List<Expert> getExperts();

	GdmProblemCurrentConsensus getCurrentConsensus();

	void addExpert(Expert expert);

	void setName(String name);

	void setDescription(String description);

}