package com.tdm.server.logic.service;

import java.util.Collection;

import com.tdm.common.dto.GdmProblemId;
import com.tdm.common.dto.SolutionIdea;
import com.tdm.common.dto.SolutionIdeaId;

public interface SolutionIdeaService {

	Collection<SolutionIdea> retrieveSolutionIdeasForProblem(
			GdmProblemId problemId);

	SolutionIdea createAndAddSolutionIdea(GdmProblemId problemId,
			String ideaName);

	SolutionIdea getSolutionIdea(GdmProblemId problemId, SolutionIdeaId ideaId);

}