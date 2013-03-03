package com.tdm.server.logic.service;

import java.util.Collection;

import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.model.SolutionIdea;
import com.tdm.server.logic.model.SolutionIdeaId;

public interface SolutionIdeaService {

	Collection<SolutionIdea> retrieveSolutionIdeasForProblem(
			GdmProblemId problemId);

	SolutionIdea createAndAddSolutionIdea(GdmProblemId problemId,
			String ideaName);

	SolutionIdea getSolutionIdea(GdmProblemId problemId, SolutionIdeaId ideaId);

}