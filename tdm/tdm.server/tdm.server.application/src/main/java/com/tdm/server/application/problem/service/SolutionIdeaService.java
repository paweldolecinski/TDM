package com.tdm.server.application.problem.service;

import java.util.Collection;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.problem.vo.GdmProblemKey;

public interface SolutionIdeaService {

	Collection<SolutionIdea> retrieveSolutionIdeasForProblem(
			GdmProblemKey problemId);

	void createAndAddSolutionIdea(GdmProblemKey problemId,
			String ideaName);

	SolutionIdea getSolutionIdea(GdmProblemKey problemId, SolutionIdeaId ideaId);

}