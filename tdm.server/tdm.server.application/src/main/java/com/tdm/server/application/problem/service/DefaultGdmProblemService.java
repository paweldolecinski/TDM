package com.tdm.server.application.problem.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;

@Service
public class DefaultGdmProblemService implements GdmProblemService {

	@Autowired
	ProblemRepository problemDao;

	protected DefaultGdmProblemService() {
	}

	DefaultGdmProblemService(ProblemRepository problemDao) {
		this.problemDao = problemDao;
	}

	@Override
	public Problem createProblem(Problem problem) {
		return problemDao.create(problem);
	}

	@Override
	public Problem retrieveProblem(ProblemId id) {
		Problem read = problemDao.read(id);
		return read;
	}

	@Override
	public ExpertId getOwnerOfProblem(ProblemId problemId) {
		Problem read = problemDao.read(problemId);
		Set<Expert> experts = read.getExperts();
		for (Expert id : experts) {
			if (ExpertRole.OWNER == id.getRole())
				return new ExpertId(id.getId());
		}
		throw new IllegalStateException("No owner for problem id: "
				+ problemId.getIdString());
	}

	@Override
	public void setOwnerOfProblem(ProblemId problemId, ExpertId expertId) {
		Problem read = problemDao.read(problemId);
		// read.addExpert(new Expert(expertId.getIdString(), ExpertRole.OWNER));
		problemDao.create(read);
	}

	@Override
	public Set<Expert> retrieveExpertsAssignedToProblem(ProblemId problemId) {
		Problem read = problemDao.read(problemId);
		Set<Expert> experts = read.getExperts();
		return experts;
	}

	@Override
	public Set<Expert> retrieveModeratorsOfProblem(ProblemId problemId) {
		Problem read = problemDao.read(problemId);
		Set<Expert> experts = read.getExperts();
		Set<Expert> res = new HashSet<Expert>();
		for (Expert id : experts) {
			if (ExpertRole.MODERATOR == id.getRole()
					|| ExpertRole.OWNER == id.getRole())
				res.add(id);
		}
		return res;
	}

	@Override
	public void assignExpertToProblem(ProblemId problemId, ExpertId expertId,
			ExpertRole expertRole) {
		Problem read = problemDao.read(problemId);
//		read.addExpert(new Expert(expertId.getIdString(), expertRole));
		problemDao.create(read);
	}

}
