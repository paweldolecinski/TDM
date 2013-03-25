package com.tdm.server.web.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.server.application.problem.service.DefaultGdmProblemService;

@Controller
@RequestMapping("/search")
public final class SearchController {

	private final DefaultGdmProblemService service;

	@Autowired
	public SearchController(DefaultGdmProblemService service) {
		this.service = service;
	}

	@RequestMapping(value = "/problems.json/{result_type}/{q}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Set<GdmProblem> getProblem(@PathVariable String result_type,
			@PathVariable String q) {

		return Collections.<GdmProblem> emptySet();
	}

}
