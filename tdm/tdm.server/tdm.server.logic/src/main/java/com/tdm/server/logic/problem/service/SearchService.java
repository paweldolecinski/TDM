package com.tdm.server.logic.problem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.tdm.common.dto.ExpertId;
import com.tdm.common.dto.ExpertRole;
import com.tdm.common.dto.GdmProblem;
import com.tdm.common.dto.Problem;
import com.tdm.server.datastore.dao.ProblemDao;
import com.tdm.server.logic.model.GdmProblemImpl;

public class SearchService {

	ProblemDao problemDao;

	public SearchService(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	public Collection<GdmProblem> retrieveProblemsForExpert(ExpertId id) {
		List<GdmProblem> problemsIds = new ArrayList<GdmProblem>();

		Collection<Problem> problems = problemDao.findAllAssignedTo(id
				.getId());
		for (Problem problemDTO : problems) {
			problemsIds.add(GdmProblemImpl.dtoToModel(problemDTO));
		}

		return problemsIds;
	}

	public Collection<GdmProblem> retrieveProblemsOwnedByExpert(ExpertId id) {
		List<GdmProblem> problemsIds = new ArrayList<GdmProblem>();

		Collection<Problem> problems = problemDao.findAllAssignedTo(
				id.getId(), ExpertRole.OWNER.name());
		for (Problem problemDTO : problems) {
			problemsIds.add(GdmProblemImpl.dtoToModel(problemDTO));
		}

		return problemsIds;
	}

	public Collection<GdmProblem> retrieveProblemsModeratedByExpert(ExpertId id) {
		List<GdmProblem> problemsIds = new ArrayList<GdmProblem>();

		Collection<Problem> problems = problemDao.findAllAssignedTo(
				id.getId(), ExpertRole.MODERATOR.name());
		for (Problem problemDTO : problems) {
			problemsIds.add(GdmProblemImpl.dtoToModel(problemDTO));
		}

		return problemsIds;
	}

}
