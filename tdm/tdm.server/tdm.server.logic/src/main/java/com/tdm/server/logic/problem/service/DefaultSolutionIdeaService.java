package com.tdm.server.logic.problem.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.tdm.common.dto.GdmProblemId;
import com.tdm.common.dto.SolutionIdea;
import com.tdm.common.dto.SolutionIdeaId;
import com.tdm.server.datastore.dao.ProblemDao;
import com.tdm.server.datastore.dao.SolutionIdeaDao;
import com.tdm.server.logic.service.SolutionIdeaService;

public class DefaultSolutionIdeaService implements SolutionIdeaService {

	ProblemDao problemDao;
	SolutionIdeaDao solutionIdeaDao;

	public DefaultSolutionIdeaService(ProblemDao problemDao,
			SolutionIdeaDao solutionIdeaDao) {
		this.problemDao = problemDao;
		this.solutionIdeaDao = solutionIdeaDao;
	}

	@Override
	public Collection<SolutionIdea> retrieveSolutionIdeasForProblem(
			GdmProblemId problemId) {
		Set<SolutionIdea> solutions = new HashSet<SolutionIdea>();
		Collection<SolutionIdea> findAllAssignedTo = solutionIdeaDao
				.findAllAssignedTo(problemId.getId());
		for (SolutionIdea solutionIdeaDTO : findAllAssignedTo) {
			solutions.add(solutionIdeaDTO);
		}
		return solutions;
	}

	@Override
	public SolutionIdea createAndAddSolutionIdea(GdmProblemId problemId,
			String ideaName) {
		SolutionIdea create = solutionIdeaDao.create(problemId.getId(),
				ideaName);
		return create;
	}

	@Override
	public SolutionIdea getSolutionIdea(GdmProblemId problemId,
			SolutionIdeaId ideaId) {
		SolutionIdea ideaDto = solutionIdeaDao.read(problemId.getId(),
				ideaId.getId());
		return ideaDto;
	}

}
