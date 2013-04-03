package com.tdm.server.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.dto.GdmProblemIdDto;
import com.tdm.server.application.problem.service.DefaultGdmProblemService;
import com.tdm.server.application.problem.service.SolutionIdeaService;

@Controller
@RequestMapping("/problems")
public final class ProblemController {

	private final DefaultGdmProblemService problemService;
	private final SolutionIdeaService ideaService;

	@Autowired
	public ProblemController(DefaultGdmProblemService problemService,
			SolutionIdeaService ideaService) {
		this.problemService = problemService;
		this.ideaService = ideaService;
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public GdmProblem getProblem(@PathVariable String id) {

		return problemService.retrieveProblem(new GdmProblemIdDto(id));
	}

	@RequestMapping(value = "/ideas/{problemId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<SolutionIdea> getSolutionIdeasForProblem(
			@PathVariable String problemId) {

		Collection<SolutionIdea> ideas = ideaService
				.retrieveSolutionIdeasForProblem(new GdmProblemIdDto(problemId));
		return new ArrayList<SolutionIdea>(ideas);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void createProblem(@RequestBody MultiValueMap<String, String> body) {

		problemService.addProblem(body.getFirst("name"),
				body.getFirst("description"), new Date());
	}

	@RequestMapping(value = "/{problemId}/idea", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public void createIdeaForProblem(@PathVariable String problemId,
			@RequestBody MultiValueMap<String, String> body) {

		ideaService.createAndAddSolutionIdea(new GdmProblemIdDto(problemId),
				body.getFirst("ideaName"));
	}

}
