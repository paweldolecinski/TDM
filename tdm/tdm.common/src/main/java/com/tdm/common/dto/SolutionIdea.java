package com.tdm.common.dto;

import java.util.Date;

public interface SolutionIdea {

	String getName();

	String getDetails();

	Date getCreation();

	GdmProblemId getProblemId();

	SolutionIdeaId getId();

}