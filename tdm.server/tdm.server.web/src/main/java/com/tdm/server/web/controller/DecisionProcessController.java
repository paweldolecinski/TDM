package com.tdm.server.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tdm.domain.model.expert.dto.ExpertsInvitationDTO;
import com.tdm.domain.model.preferences.dto.SolutionPreferencesDTO;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.server.application.decision.service.DecisionProcessService;
import com.tdm.server.application.problem.service.InvitationService;

@Controller
@RequestMapping("/decision/{problemId}")
public final class DecisionProcessController {

	@Inject
	InvitationService invitationService;
	@Inject
	DecisionProcessService decisionProcessService;
	
	public DecisionProcessController() {
	}

	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public void vote(@PathVariable long problemId,
			@RequestBody SolutionPreferencesDTO preferences) {
		
		decisionProcessService.vote();
	}

	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public void inviteExpert(@PathVariable String problemId,
			@RequestBody ExpertsInvitationDTO invitation,
			HttpServletResponse httpResponse_p, WebRequest request_p) {
		invitationService.inviteExperts(new ProblemId(problemId),
				invitation.getEmails(), invitation.getMessage());
		httpResponse_p.setStatus(HttpStatus.OK.value());
	}
}
