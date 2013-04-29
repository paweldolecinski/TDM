package com.tdm.domain.model.problem.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface Problem {

	String getKey();

	@NotNull(message = "Title cannot be empty.")
	@Size(min = 1, message = "Title must be at least 1 character long.")
	String getName();

	@Size(max = 500, message = "Title must be at most 500 characters long.")
	String getDescription();

	Date getCreationDate();

	CurrentConsensus getCurrentConsensus();

	void setName(String name);

	void setDescription(String description);

}