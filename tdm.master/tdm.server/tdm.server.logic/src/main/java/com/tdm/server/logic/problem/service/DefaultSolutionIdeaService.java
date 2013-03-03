package com.tdm.server.logic.problem.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dao.SolutionIdeaDao;
import pl.dolecinski.subdicium.server.datastore.dto.SolutionIdeaDTO;

import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.model.SolutionIdea;
import com.tdm.server.logic.model.SolutionIdeaId;
import com.tdm.server.logic.model.SolutionIdeaImpl;
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
		Collection<SolutionIdeaDTO> findAllAssignedTo = solutionIdeaDao
				.findAllAssignedTo(problemId.getId());
		for (SolutionIdeaDTO solutionIdeaDTO : findAllAssignedTo) {
			solutions.add(SolutionIdeaImpl.dtoToModel(solutionIdeaDTO));
		}
		return solutions;
	}

	@Override
	public SolutionIdea createAndAddSolutionIdea(GdmProblemId problemId,
			String ideaName) {
		SolutionIdeaDTO create = solutionIdeaDao.create(problemId.getId(),
				ideaName);
		SolutionIdea solutionIdea = SolutionIdeaImpl.dtoToModel(create);
		return solutionIdea;
	}

	@Override
	public SolutionIdea getSolutionIdea(GdmProblemId problemId, SolutionIdeaId ideaId) {
		SolutionIdeaDTO ideaDto = solutionIdeaDao.read(problemId.getId(), ideaId.getId());
		SolutionIdea idea = SolutionIdeaImpl.dtoToModel(ideaDto);
		return idea;
	}

}
