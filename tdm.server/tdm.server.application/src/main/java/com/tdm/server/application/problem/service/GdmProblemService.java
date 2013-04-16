package com.tdm.server.application.problem.service;

import java.util.List;

import com.tdm.domain.model.expert.vo.Expert;
import com.tdm.domain.model.expert.vo.ExpertId;
import com.tdm.domain.model.expert.vo.ExpertRole;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.GdmProblemKey;
import com.tdm.domain.model.problem.vo.dto.GdmProblemDto;

public interface GdmProblemService {

	GdmProblem retrieveProblem(GdmProblemKey id);

	ExpertId getOwnerOfProblem(GdmProblemKey problemId);

	void setOwnerOfProblem(GdmProblemKey problemId, ExpertId id);

	List<Expert> retrieveExpertsAssignedToProblem(GdmProblemKey problemId);

	List<Expert> retrieveModeratorsOfProblem(GdmProblemKey problemId);

	void assignExpertToProblem(GdmProblemKey problemId, ExpertId expertId,
			ExpertRole expertRole);

	GdmProblemDto createProblem(GdmProblemDto problem);

}