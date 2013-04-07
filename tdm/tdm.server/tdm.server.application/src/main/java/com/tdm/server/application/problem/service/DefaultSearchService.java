package com.tdm.server.application.problem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.expert.vo.ExpertId;
import com.tdm.domain.model.expert.vo.ExpertRole;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.domain.model.problem.vo.GdmProblem;

@Service
public class DefaultSearchService implements SearchService {

	@Autowired
	ProblemRepository problemDao;

	protected DefaultSearchService() {
	}

	public DefaultSearchService(ProblemRepository problemDao) {
		this.problemDao = problemDao;
	}

	@Override
	public List<GdmProblem> retrieveProblemsForExpert(ExpertId id) {

		List<GdmProblem> problems = problemDao.findAllAssignedTo(id);

		return problems;
	}

	@Override
	public List<GdmProblem> retrieveProblemsOwnedByExpert(ExpertId id) {

		List<GdmProblem> problems = problemDao.findAllAssignedTo(id,
				ExpertRole.OWNER);

		return problems;
	}

	@Override
	public List<GdmProblem> retrieveProblemsModeratedByExpert(ExpertId id) {

		List<GdmProblem> problems = problemDao.findAllAssignedTo(id,
				ExpertRole.MODERATOR);

		return problems;
	}

}
