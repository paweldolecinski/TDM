package com.tdm.server.application.problem.service;

import java.util.Date;
import java.util.List;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.GdmProblemId;

public interface GdmProblemService {

	GdmProblem retrieveProblem(GdmProblemId id);

	ExpertId getOwnerOfProblem(GdmProblemId problemId);

	void setOwnerOfProblem(GdmProblemId problemId, ExpertId id);

	List<Expert> retrieveExpertsAssignedToProblem(GdmProblemId problemId);

	List<Expert> retrieveModeratorsOfProblem(GdmProblemId problemId);

	void assignExpertToProblem(GdmProblemId problemId, ExpertId expertId,
			ExpertRole expertRole);

	void addProblem(String name, String description, Date created);

}