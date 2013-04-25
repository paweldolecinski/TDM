package com.tdm.server.application.problem.service;

import java.util.List;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.problem.ProblemId;

public interface SolutionIdeaService {

	List<SolutionIdea> retrieveSolutionIdeasForProblem(ProblemId problemId);

	SolutionIdea addSolutionIdea(SolutionIdea idea, ProblemId problemId);

	SolutionIdea getSolutionIdea(ProblemId problemId, SolutionIdeaId ideaId);

}