package com.tdm.server.application.problem.service;

import java.util.Set;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;

public interface GdmProblemService {

	Problem retrieveProblem(ProblemId id);

	ExpertId getOwnerOfProblem(ProblemId problemId);

	void setOwnerOfProblem(ProblemId problemId, ExpertId id);

	Set<Expert> retrieveExpertsAssignedToProblem(ProblemId problemId);

	Set<Expert> retrieveModeratorsOfProblem(ProblemId problemId);

	void assignExpertToProblem(ProblemId problemId, ExpertId expertId,
			ExpertRole expertRole);

	Problem createProblem(Problem problem);

}