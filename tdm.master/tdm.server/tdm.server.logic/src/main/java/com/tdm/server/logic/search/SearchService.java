package com.tdm.server.logic.search;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblemId;

public class SearchService {

	ProblemDao problemDao;

	public SearchService(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	public Collection<GdmProblemId> retrieveProblemsIdsForExpert(ExpertId id) {
		Set<GdmProblemId> problemsIds = new HashSet<GdmProblemId>();

		Collection<ProblemDTO> problems = problemDao.findAllAssignedTo(id
				.getId());
		for (ProblemDTO problemDTO : problems) {
			problemsIds.add(GdmProblemId.create(problemDTO.getId()));
		}

		return problemsIds;
	}

	public Collection<GdmProblemId> retrieveProblemsOwnedByExpert(ExpertId id) {
		Set<GdmProblemId> problemsIds = new HashSet<GdmProblemId>();

		Collection<ProblemDTO> problems = problemDao.findAllAssignedTo(
				id.getId(), ExpertRole.OWNER.name());
		for (ProblemDTO problemDTO : problems) {
			problemsIds.add(GdmProblemId.create(problemDTO.getId()));
		}

		return problemsIds;
	}

	public Collection<GdmProblemId> retrieveProblemsModeratedByExpert(
			ExpertId id) {
		Set<GdmProblemId> problemsIds = new HashSet<GdmProblemId>();

		Collection<ProblemDTO> problems = problemDao.findAllAssignedTo(
				id.getId(), ExpertRole.MODERATOR.name());
		for (ProblemDTO problemDTO : problems) {
			problemsIds.add(GdmProblemId.create(problemDTO.getId()));
		}

		return problemsIds;
	}
}
