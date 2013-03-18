package com.tdm.server.logic.service;

import java.util.Collection;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemId;

public interface GdmProblemService {

	GdmProblem createEmptyProblem();

	GdmProblem addProblem(GdmProblem problem);

	GdmProblem retrieveProblem(GdmProblemId id);

	ExpertId getOwnerOfProblem(GdmProblemId problemId);

	void setOwnerOfProblem(GdmProblemId problemId, ExpertId id);

	Collection<ExpertId> retrieveExpertsAssignedToProblem(GdmProblemId problemId);

	Collection<ExpertId> retrieveModeratorsOfProblem(GdmProblemId problemId);

	void assignExpertToProblem(GdmProblemId problemId, ExpertId expertId,
			ExpertRole expertRole);

}