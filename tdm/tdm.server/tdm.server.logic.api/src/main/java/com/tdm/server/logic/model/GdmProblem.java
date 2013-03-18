package com.tdm.server.logic.model;

public interface GdmProblem {

	void setName(String name);

	void setDescription(String description);

	String getName();

	String getDescription();

	GdmProblemId getId();

}