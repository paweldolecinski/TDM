package com.tdm.server.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.dto.SolutionIdeaDTO;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.dto.ProblemDTO;
import com.tdm.server.application.problem.service.GdmProblemService;
import com.tdm.server.application.problem.service.SolutionIdeaService;
import com.tdm.server.web.assembler.GdmProblemDtoAssembler;
import com.tdm.server.web.assembler.SolutionIdeaDtoAssembler;

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
	public ProblemDTO getProblem(@PathVariable String problemId) {

		Problem retrieveProblem = problemService.retrieveProblem(new ProblemId(
				problemId));
		GdmProblemDtoAssembler ass = new GdmProblemDtoAssembler();

		return ass.fromEntity(retrieveProblem);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ProblemDTO createProblem(@RequestBody ProblemDTO problem,
			HttpServletResponse httpResponse_p, WebRequest request_p) {
		GdmProblemDtoAssembler ass = new GdmProblemDtoAssembler();
		Problem entity = ass.toEntity(problem);
		try {
			Problem createdProblem = problemService.createProblem(entity);

			httpResponse_p.setStatus(HttpStatus.CREATED.value());
			httpResponse_p.setHeader("Location", request_p.getContextPath()
					+ "/problems/" + createdProblem.getEncodedKey());

			return ass.fromEntity(createdProblem);
		} catch (Exception e) {
			httpResponse_p.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			return null;
		}
	}

	@RequestMapping(value = "/{problemId}/ideas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SolutionIdeaDTO> getSolutionIdeasForProblem(
			@PathVariable String problemId) {
		SolutionIdeaDtoAssembler ass = new SolutionIdeaDtoAssembler();
		List<SolutionIdea> ideas = ideaService
				.retrieveSolutionIdeasForProblem(new ProblemId(problemId));
		return ass.fromEntityList(ideas);
	}

	@RequestMapping(value = "/{problemId}/ideas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SolutionIdeaDTO createIdeaForProblem(@PathVariable String problemId,
			@RequestBody SolutionIdeaDTO solutionIdea,
			HttpServletResponse httpResponse_p, WebRequest request_p) {
		SolutionIdeaDtoAssembler ass = new SolutionIdeaDtoAssembler();
		SolutionIdea entity = ass.toEntity(solutionIdea, problemService);
		try {
			SolutionIdea created = ideaService.addSolutionIdea(entity);

			httpResponse_p.setStatus(HttpStatus.CREATED.value());
			httpResponse_p.setHeader("Location",
					request_p.getContextPath() + "/problems/" + problemId
							+ "/ideas/" + created.getEncodedKey());

			return ass.fromEntity(created);
		} catch (Exception e) {
			httpResponse_p.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			return null;
		}

	}

}
