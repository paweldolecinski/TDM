package com.tdm.server.application.problem.service;

import java.util.List;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.problem.Problem;

public interface SearchService {

	List<Problem> retrieveProblemsForExpert(ExpertId id);

	List<Problem> retrieveProblemsOwnedByExpert(ExpertId id);

	List<Problem> retrieveProblemsModeratedByExpert(ExpertId id);

}