package com.tdm.server.application.problem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.expert.dto.ExpertDto;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.GdmProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.domain.model.problem.dto.GdmProblemDto;

@Service
public class DefaultGdmProblemService implements GdmProblemService {

    @Autowired
    ProblemRepository problemDao;

    public DefaultGdmProblemService() {
	System.out
		.println("DefaultGdmProblemServiceDefaultGdmProblemServiceDefaultGdmProblemService");
    }

    DefaultGdmProblemService(ProblemRepository problemDao) {
	this.problemDao = problemDao;
    }

    @Override
    public void addProblem(String name, String description, Date created) {
	GdmProblem p = new GdmProblemDto();
	p.setName(name);
	p.setDescription(description);
	problemDao.store(p);
    }

    @Override
    public GdmProblem retrieveProblem(GdmProblemId id) {
	GdmProblem read = problemDao.read(id);
	return read;
    }

    @Override
    public ExpertId getOwnerOfProblem(GdmProblemId problemId) {
	GdmProblem read = problemDao.read(problemId);
	List<Expert> experts = read.getExperts();
	for (Expert id : experts) {
	    if (ExpertRole.OWNER == id.getRole())
		return id.getId();
	}
	throw new IllegalStateException("No owner for problem id: "
		+ problemId.getIdString());
    }

    @Override
    public void setOwnerOfProblem(GdmProblemId problemId, ExpertId expertId) {
	GdmProblem read = problemDao.read(problemId);
	read.addExpert(new ExpertDto(expertId, ExpertRole.OWNER));
	problemDao.store(read);
    }

    @Override
    public List<Expert> retrieveExpertsAssignedToProblem(GdmProblemId problemId) {
	GdmProblem read = problemDao.read(problemId);
	List<Expert> experts = read.getExperts();
	return experts;
    }

    @Override
    public List<Expert> retrieveModeratorsOfProblem(GdmProblemId problemId) {
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
    public void assignExpertToProblem(GdmProblemId problemId,
	    ExpertId expertId, ExpertRole expertRole) {
	GdmProblem read = problemDao.read(problemId);
	read.addExpert(new ExpertDto(expertId, expertRole));
	problemDao.store(read);
    }

}
