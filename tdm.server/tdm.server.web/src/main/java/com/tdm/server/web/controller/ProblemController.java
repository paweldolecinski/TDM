package com.tdm.server.web.controller;

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
import com.tdm.domain.model.idea.vo.SolutionIdeaDTO;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.dto.ProblemDTO;
import com.tdm.server.application.problem.service.GdmProblemService;
import com.tdm.server.application.problem.service.SolutionIdeaService;
import com.tdm.server.web.assembler.GdmProblemDtoAssembler;

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
					+ "/problems/" + createdProblem.getKey());

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

		Collection<SolutionIdea> ideas = ideaService
				.retrieveSolutionIdeasForProblem(new ProblemId(problemId));
		return null;// new ArrayList<SolutionIdea>(ideas);
	}

	@RequestMapping(value = "/{problemId}/ideas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void createIdeaForProblem(@PathVariable String problemId,
			@RequestBody MultiValueMap<String, String> body) {
		SolutionIdea solutionIdea = new SolutionIdea();
//		new ProblemId(problemId),body.getFirst("ideaName")
		ideaService.addSolutionIdea(solutionIdea);
	}

}
