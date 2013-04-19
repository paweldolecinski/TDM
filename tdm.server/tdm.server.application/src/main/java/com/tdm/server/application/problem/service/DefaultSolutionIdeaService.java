package com.tdm.server.application.problem.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;

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
			ProblemId problemId) {
		Set<SolutionIdea> solutions = new HashSet<SolutionIdea>();
		Collection<SolutionIdea> findAllAssignedTo = solutionIdeaDao
				.findAllAssignedTo(problemId);
		for (SolutionIdea solutionIdeaDTO : findAllAssignedTo) {
			solutions.add(solutionIdeaDTO);
		}
		return solutions;
	}

	@Override
	public void addSolutionIdea(SolutionIdea idea) {
		solutionIdeaDao.store(idea);
	}

	@Override
	public SolutionIdea getSolutionIdea(ProblemId problemId,
			SolutionIdeaId ideaId) {
		SolutionIdea ideaDto = solutionIdeaDao.read(problemId, ideaId);
		return ideaDto;
	}

}
