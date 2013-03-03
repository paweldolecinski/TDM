package com.tdm.server.logic.model;

import java.util.Date;

public interface SolutionIdea {

	String getName();

	String getDetails();

	Date getCreation();

	public abstract GdmProblemId getProblemId();

	public abstract SolutionIdeaId getId();

}