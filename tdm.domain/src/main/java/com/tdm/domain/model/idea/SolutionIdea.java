package com.tdm.domain.model.idea;

import java.util.Date;

import com.tdm.domain.model.problem.vo.GdmProblemKey;

public interface SolutionIdea {

	String getName();

	String getDetails();

	Date getCreation();

	GdmProblemKey getProblemId();

	SolutionIdeaId getId();

}