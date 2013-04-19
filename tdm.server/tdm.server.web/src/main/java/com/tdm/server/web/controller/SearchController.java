package com.tdm.server.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.dto.ProblemDTO;
import com.tdm.server.application.problem.service.SearchService;
import com.tdm.server.web.assembler.GdmProblemDtoAssembler;

@Controller
@RequestMapping("/search")
public final class SearchController {

	private final SearchService service;

	@Autowired
	public SearchController(SearchService service) {
		this.service = service;
	}

	@RequestMapping(value = "/problems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProblemDTO> getProblems(
			@RequestParam(value = "q", required = false) String filtr) {
		List<Problem> problems = service.retrieveProblemsForExpert(null);
		GdmProblemDtoAssembler ass = new GdmProblemDtoAssembler();

		return ass.fromEntityList(problems);
	}

}
