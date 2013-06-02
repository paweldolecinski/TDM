package com.tdm.server.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.dto.ExpertsInvitationDTO;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.dto.SolutionIdea;
import com.tdm.domain.model.preferences.dto.SolutionPreferencesDTO;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.dto.CurrentConsensusDTO;
import com.tdm.server.application.decision.preference.SolutionIdeaUtilityList;
import com.tdm.server.application.decision.service.DecisionProcessService;
import com.tdm.server.application.problem.service.InvitationService;
import com.tdm.server.application.problem.service.SolutionIdeaService;
import com.tdm.server.web.assembler.PreferencesDtoAssembler;
import com.tdm.server.web.assembler.SolutionIdeaDtoAssembler;

@Controller
@RequestMapping("/decision/{problemId}")
public final class DecisionProcessController {

	@Inject
	private InvitationService invitationService;
	@Inject
	private DecisionProcessService decisionProcessService;
	@Inject
	private SolutionIdeaService ideaService;

	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public void vote(@PathVariable String problemId,
			@RequestBody SolutionPreferencesDTO preferences,
			HttpServletResponse httpResponse_p, Principal principal) {
		if (problemId.equals(preferences.getProblemId())) {

			SolutionIdeaUtilityList prefs = new PreferencesDtoAssembler()
					.toModel(preferences);
			decisionProcessService.vote(new ProblemId(problemId), new ExpertId(
					principal.getName()), prefs);
			httpResponse_p.setStatus(HttpStatus.OK.value());
		} else {
			throw new IllegalStateException(
					"Problem ID different than one connected with preferences.");
		}
	}

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	@ResponseBody
	public CurrentConsensusDTO getResult(@PathVariable String problemId) {

		CurrentConsensusDTO result = new CurrentConsensusDTO();

		ProblemId problem = new ProblemId(problemId);
		List<SolutionIdeaId> currentRanking = decisionProcessService
				.getCurrentResult(problem);
		SolutionIdeaDtoAssembler ass = new SolutionIdeaDtoAssembler();
		List<SolutionIdea> ideas = new ArrayList<SolutionIdea>();
		for (SolutionIdeaId solutionIdeaId : currentRanking) {
			ideas.add(ass.fromEntity(ideaService.getSolutionIdea(problem,
					solutionIdeaId)));
		}
		result.setProblemId(problemId);
		result.setRanking(ideas);
		return result;
	}

	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	public void inviteExpert(@PathVariable String problemId,
			@RequestBody ExpertsInvitationDTO invitation,
			HttpServletResponse httpResponse_p, WebRequest request_p) {
		invitationService.inviteExperts(new ProblemId(problemId),
				invitation.getEmails(), invitation.getMessage());
		httpResponse_p.setStatus(HttpStatus.OK.value());
	}
}
