package com.tdm.server.application.problem.service;

import java.util.Collection;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.ProblemRepository;

public class SearchService {

	ProblemRepository problemDao;

	public SearchService(ProblemRepository problemDao) {
		this.problemDao = problemDao;
	}

	public Collection<GdmProblem> retrieveProblemsForExpert(ExpertId id) {

		Collection<GdmProblem> problems = problemDao.findAllAssignedTo(id);

		return problems;
	}

	public Collection<GdmProblem> retrieveProblemsOwnedByExpert(ExpertId id) {

		Collection<GdmProblem> problems = problemDao.findAllAssignedTo(id,
				ExpertRole.OWNER);

		return problems;
	}

	public Collection<GdmProblem> retrieveProblemsModeratedByExpert(ExpertId id) {

		Collection<GdmProblem> problems = problemDao.findAllAssignedTo(id,
				ExpertRole.MODERATOR);

		return problems;
	}

}
