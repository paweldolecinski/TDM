package com.tdm.server.logic.problem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemImpl;

public class SearchService {

	ProblemDao problemDao;

	public SearchService(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	public Collection<GdmProblem> retrieveProblemsForExpert(ExpertId id) {
		List<GdmProblem> problemsIds = new ArrayList<GdmProblem>();

		Collection<ProblemDTO> problems = problemDao.findAllAssignedTo(id
				.getId());
		for (ProblemDTO problemDTO : problems) {
			problemsIds.add(GdmProblemImpl.dtoToModel(problemDTO));
		}

		return problemsIds;
	}

	public Collection<GdmProblem> retrieveProblemsOwnedByExpert(ExpertId id) {
		List<GdmProblem> problemsIds = new ArrayList<GdmProblem>();

		Collection<ProblemDTO> problems = problemDao.findAllAssignedTo(
				id.getId(), ExpertRole.OWNER.name());
		for (ProblemDTO problemDTO : problems) {
			problemsIds.add(GdmProblemImpl.dtoToModel(problemDTO));
		}

		return problemsIds;
	}

	public Collection<GdmProblem> retrieveProblemsModeratedByExpert(ExpertId id) {
		List<GdmProblem> problemsIds = new ArrayList<GdmProblem>();

		Collection<ProblemDTO> problems = problemDao.findAllAssignedTo(
				id.getId(), ExpertRole.MODERATOR.name());
		for (ProblemDTO problemDTO : problems) {
			problemsIds.add(GdmProblemImpl.dtoToModel(problemDTO));
		}

		return problemsIds;
	}

}
