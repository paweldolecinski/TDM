package com.tdm.server.application.problem.service;

import java.util.Collection;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.problem.ProblemId;

public interface SolutionIdeaService {

	Collection<SolutionIdea> retrieveSolutionIdeasForProblem(ProblemId problemId);

	void addSolutionIdea(SolutionIdea idea);

	SolutionIdea getSolutionIdea(ProblemId problemId, SolutionIdeaId ideaId);

}