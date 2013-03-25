package com.tdm.domain.model.idea;

import java.util.Date;

import com.tdm.domain.model.problem.GdmProblemId;

public interface SolutionIdea {

	String getName();

	String getDetails();

	Date getCreation();

	GdmProblemId getProblemId();

	SolutionIdeaId getId();

}