package com.tdm.server.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.dto.GdmProblemDto;
import com.tdm.domain.model.problem.vo.dto.GdmProblemKeyDto;
import com.tdm.server.application.problem.service.GdmProblemService;
import com.tdm.server.application.problem.service.SolutionIdeaService;

@Controller
@RequestMapping("/problems")
public final class ProblemController {

	private final GdmProblemService problemService;
	private final SolutionIdeaService ideaService;

	@Autowired
	public ProblemController(GdmProblemService problemService,
			SolutionIdeaService ideaService) {
		this.problemService = problemService;
		this.ideaService = ideaService;
	}

	@RequestMapping(value = "/{problemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GdmProblem getProblem(@PathVariable String problemId) {

		return problemService.retrieveProblem(new GdmProblemKeyDto(problemId));
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	GdmProblemDto createProblem(@RequestBody GdmProblemDto problem,
			HttpServletResponse httpResponse_p, WebRequest request_p) {

		try {
			GdmProblemDto createdProblem = problemService
					.createProblem(problem);

			httpResponse_p.setStatus(HttpStatus.CREATED.value());
			httpResponse_p.setHeader("Location", request_p.getContextPath()
					+ "/problems/" + createdProblem.getKey().getId());

			return createdProblem;
		} catch (Exception e) {
			httpResponse_p.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			return null;
		}
	}

	@RequestMapping(value = "/{problemId}/ideas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SolutionIdea> getSolutionIdeasForProblem(
			@PathVariable String problemId) {

		Collection<SolutionIdea> ideas = ideaService
				.retrieveSolutionIdeasForProblem(new GdmProblemKeyDto(problemId));
		return new ArrayList<SolutionIdea>(ideas);
	}

	@RequestMapping(value = "/{problemId}/ideas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void createIdeaForProblem(@PathVariable String problemId,
			@RequestBody MultiValueMap<String, String> body) {

		ideaService.createAndAddSolutionIdea(new GdmProblemKeyDto(problemId),
				body.getFirst("ideaName"));
	}

}
