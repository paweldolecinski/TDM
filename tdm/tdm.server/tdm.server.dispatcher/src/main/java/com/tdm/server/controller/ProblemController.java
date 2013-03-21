package com.tdm.server.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.common.dto.GdmProblem;
import com.tdm.common.dto.GdmProblemId;
import com.tdm.common.dto.SolutionIdea;
import com.tdm.server.logic.service.GdmProblemService;
import com.tdm.server.logic.service.SolutionIdeaService;

@Controller
@RequestMapping("/problems")
final class ProblemController {

	GdmProblemService problemService;
	SolutionIdeaService ideaService;

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public GdmProblem getProblem(@PathVariable long id) {

		return problemService.retrieveProblem(GdmProblemId.create(id));
	}

	@RequestMapping(value = "/ideas/{problemId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<SolutionIdea> getSolutionIdeasForProblem(
			@PathVariable long problemId) {

		Collection<SolutionIdea> ideas = ideaService
				.retrieveSolutionIdeasForProblem(GdmProblemId.create(problemId));
		return new ArrayList<SolutionIdea>(ideas);
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public GdmProblem createProblem(
			@RequestBody MultiValueMap<String, String> body) {

		GdmProblem problem = problemService.createEmptyProblem();
		problem.setName(body.getFirst("name"));
		problem.setDescription(body.getFirst("description"));
		GdmProblem addedProblem = problemService.addProblem(problem);
		return addedProblem;
	}

	@RequestMapping(value = "/{problemId}/idea", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public SolutionIdea createIdeaForProblem(@PathVariable long problemId,
			@RequestBody MultiValueMap<String, String> body) {

		SolutionIdea idea = ideaService.createAndAddSolutionIdea(
				GdmProblemId.create(problemId), body.getFirst("ideaName"));
		return idea;
	}

}
