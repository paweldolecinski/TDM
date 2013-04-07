package com.tdm.server.application.problem.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.idea.dto.SolutionIdeaDto;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.domain.model.problem.vo.GdmProblemKey;

@Service
public class DefaultSolutionIdeaService implements SolutionIdeaService {

	@Autowired
	ProblemRepository problemDao;
	@Autowired
	SolutionIdeaRepository solutionIdeaDao;

	public DefaultSolutionIdeaService() {

	}

	DefaultSolutionIdeaService(ProblemRepository problemDao,
			SolutionIdeaRepository solutionIdeaDao) {
		this.problemDao = problemDao;
		this.solutionIdeaDao = solutionIdeaDao;
	}

	@Override
	public Collection<SolutionIdea> retrieveSolutionIdeasForProblem(
			GdmProblemKey problemId) {
		Set<SolutionIdea> solutions = new HashSet<SolutionIdea>();
		Collection<SolutionIdea> findAllAssignedTo = solutionIdeaDao
				.findAllAssignedTo(problemId);
		for (SolutionIdea solutionIdeaDTO : findAllAssignedTo) {
			solutions.add(solutionIdeaDTO);
		}
		return solutions;
	}

	@Override
	public void createAndAddSolutionIdea(GdmProblemKey problemId, String ideaName) {
		SolutionIdeaDto idea = new SolutionIdeaDto();
		idea.setProblemId(problemId);
		idea.setName(ideaName);
		solutionIdeaDao.store(idea);
	}

	@Override
	public SolutionIdea getSolutionIdea(GdmProblemKey problemId,
			SolutionIdeaId ideaId) {
		SolutionIdea ideaDto = solutionIdeaDao.read(problemId, ideaId);
		return ideaDto;
	}

}
