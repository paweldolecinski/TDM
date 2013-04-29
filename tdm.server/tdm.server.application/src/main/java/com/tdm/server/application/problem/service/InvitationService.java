package com.tdm.server.application.problem.service;

import java.util.List;

import com.tdm.domain.model.problem.ProblemId;

public interface InvitationService {

	void inviteExperts(ProblemId problemId, List<String> emails, String msg);

}