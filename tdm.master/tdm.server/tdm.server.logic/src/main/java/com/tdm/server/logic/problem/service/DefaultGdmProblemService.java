package com.tdm.server.logic.problem.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.model.GdmProblemImpl;
import com.tdm.server.logic.service.GdmProblemService;

public class DefaultGdmProblemService implements GdmProblemService {

	ProblemDao problemDao;

	public DefaultGdmProblemService(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	@Override
	public GdmProblem createEmptyProblem() {
		return new GdmProblemImpl(GdmProblemId.EMPTY_ID);
	}

	@Override
	public GdmProblem addProblem(GdmProblem problem) {
		if (problem.getId() != GdmProblemId.EMPTY_ID) {
			throw new UnsupportedOperationException(
					"Adding problem requires empty id");
		}

		ProblemDTO created = problemDao.create(problem.getName(),
				problem.getDescription());
		return GdmProblemImpl.dtoToModel(created);
	}

	@Override
	public GdmProblem retrieveProblem(GdmProblemId id) {
		ProblemDTO read = problemDao.read(id.getId());
		GdmProblem dtoToModel = GdmProblemImpl.dtoToModel(read);
		return dtoToModel;
	}

	@Override
	public ExpertId getOwnerOfProblem(GdmProblemId problemId) {
		ProblemDTO read = problemDao.read(problemId.getId());
		Map<Long, String> experts = read.getAssignedExpertsWithRoles();
		for (Entry<Long, String> id : experts.entrySet()) {
			if (ExpertRole.OWNER.name().equals(id.getValue()))
				return ExpertId.create(id.getKey());
		}
		throw new IllegalStateException("No owner for problem id: "
				+ problemId.getId());
	}

	@Override
	public void setOwnerOfProblem(GdmProblemId problemId, ExpertId expertId) {
		ProblemDTO read = problemDao.read(problemId.getId());
		read.addExpert(expertId.getId(), ExpertRole.OWNER.name());
		problemDao.update(read);
	}

	@Override
	public Collection<ExpertId> retrieveExpertsAssignedToProblem(
			GdmProblemId problemId) {
		ProblemDTO read = problemDao.read(problemId.getId());
		Map<Long, String> experts = read.getAssignedExpertsWithRoles();
		Set<ExpertId> res = new HashSet<ExpertId>();
		for (long id : experts.keySet()) {
			res.add(ExpertId.create(id));
		}
		return res;
	}

	@Override
	public Collection<ExpertId> retrieveModeratorsOfProblem(
			GdmProblemId problemId) {
		ProblemDTO read = problemDao.read(problemId.getId());
		Map<Long, String> experts = read.getAssignedExpertsWithRoles();
		Set<ExpertId> res = new HashSet<ExpertId>();
		for (Entry<Long, String> id : experts.entrySet()) {
			if (ExpertRole.MODERATOR.name().equals(id.getValue())
					|| ExpertRole.OWNER.name().equals(id.getValue()))
				res.add(ExpertId.create(id.getKey()));
		}
		return res;
	}

	@Override
	public void assignExpertToProblem(GdmProblemId problemId,
			ExpertId expertId, ExpertRole expertRole) {
		ProblemDTO read = problemDao.read(problemId.getId());
		read.addExpert(expertId.getId(), expertRole.name());
		problemDao.update(read);
	}

}
