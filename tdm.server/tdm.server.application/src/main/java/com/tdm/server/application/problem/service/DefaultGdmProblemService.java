package com.tdm.server.application.problem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.expert.vo.Expert;
import com.tdm.domain.model.expert.vo.ExpertId;
import com.tdm.domain.model.expert.vo.ExpertRole;
import com.tdm.domain.model.expert.vo.dto.ExpertDto;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.GdmProblemKey;
import com.tdm.domain.model.problem.vo.dto.GdmProblemDto;

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
	public GdmProblemDto createProblem(GdmProblemDto problem) {
		problem.setCreationDate(new Date());
		return problemDao.create(problem);
	}

	@Override
	public GdmProblem retrieveProblem(GdmProblemKey id) {
		GdmProblem read = problemDao.read(id);
		return read;
	}

	@Override
	public ExpertId getOwnerOfProblem(GdmProblemKey problemId) {
		GdmProblem read = problemDao.read(problemId);
		List<Expert> experts = read.getExperts();
		for (Expert id : experts) {
			if (ExpertRole.OWNER == id.getRole())
				return id.getId();
		}
		throw new IllegalStateException("No owner for problem id: "
				+ problemId.getId());
	}

	@Override
	public void setOwnerOfProblem(GdmProblemKey problemId, ExpertId expertId) {
		GdmProblem read = problemDao.read(problemId);
		read.addExpert(new ExpertDto(expertId, ExpertRole.OWNER));
		problemDao.create(read);
	}

	@Override
	public List<Expert> retrieveExpertsAssignedToProblem(GdmProblemKey problemId) {
		GdmProblem read = problemDao.read(problemId);
		List<Expert> experts = read.getExperts();
		return experts;
	}

	@Override
	public List<Expert> retrieveModeratorsOfProblem(GdmProblemKey problemId) {
		GdmProblem read = problemDao.read(problemId);
		List<Expert> experts = read.getExperts();
		List<Expert> res = new ArrayList<>();
		for (Expert id : experts) {
			if (ExpertRole.MODERATOR == id.getRole()
					|| ExpertRole.OWNER == id.getRole())
				res.add(id);
		}
		return res;
	}

	@Override
	public void assignExpertToProblem(GdmProblemKey problemId,
			ExpertId expertId, ExpertRole expertRole) {
		GdmProblem read = problemDao.read(problemId);
		read.addExpert(new ExpertDto(expertId, expertRole));
		problemDao.create(read);
	}

}
