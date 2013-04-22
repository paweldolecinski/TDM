package com.tdm.server.application.problem.service;

import java.util.List;

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
	public List<SolutionIdea> retrieveSolutionIdeasForProblem(
			ProblemId problemId) {
		List<SolutionIdea> findAllAssignedTo = solutionIdeaDao
				.findAllAssignedTo(problemId);
		return findAllAssignedTo;
	}

	@Override
	public SolutionIdea addSolutionIdea(SolutionIdea idea) {
		return solutionIdeaDao.create(idea);
	}

	@Override
	public SolutionIdea getSolutionIdea(ProblemId problemId,
			SolutionIdeaId ideaId) {
		SolutionIdea ideaDto = solutionIdeaDao.read(problemId, ideaId);
		return ideaDto;
	}

}
