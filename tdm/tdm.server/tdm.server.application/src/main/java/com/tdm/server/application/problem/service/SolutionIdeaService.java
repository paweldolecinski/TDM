package com.tdm.server.application.problem.service;

import java.util.Collection;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.problem.GdmProblemId;

public interface SolutionIdeaService {

	Collection<SolutionIdea> retrieveSolutionIdeasForProblem(
			GdmProblemId problemId);

	void createAndAddSolutionIdea(GdmProblemId problemId,
			String ideaName);

	SolutionIdea getSolutionIdea(GdmProblemId problemId, SolutionIdeaId ideaId);

}