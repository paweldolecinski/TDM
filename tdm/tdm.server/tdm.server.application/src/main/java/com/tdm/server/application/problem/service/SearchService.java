package com.tdm.server.application.problem.service;

import java.util.List;

import com.tdm.domain.model.expert.vo.ExpertId;
import com.tdm.domain.model.problem.vo.GdmProblem;

public interface SearchService {

	List<GdmProblem> retrieveProblemsForExpert(ExpertId id);

	List<GdmProblem> retrieveProblemsOwnedByExpert(ExpertId id);

	List<GdmProblem> retrieveProblemsModeratedByExpert(ExpertId id);

}