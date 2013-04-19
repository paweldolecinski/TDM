package com.tdm.server.application.problem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemRepository;

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
	public List<Problem> retrieveProblemsForExpert(ExpertId id) {

		List<Problem> problems = problemDao.findAllAssignedTo(id);

		return problems;
	}

	@Override
	public List<Problem> retrieveProblemsOwnedByExpert(ExpertId id) {

		List<Problem> problems = problemDao.findAllAssignedTo(id,
				ExpertRole.OWNER);

		return problems;
	}

	@Override
	public List<Problem> retrieveProblemsModeratedByExpert(ExpertId id) {

		List<Problem> problems = problemDao.findAllAssignedTo(id,
				ExpertRole.MODERATOR);

		return problems;
	}

}
