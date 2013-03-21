package com.tdm.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.server.logic.service.GdmProblemService;

@Controller
@RequestMapping("/problem/{problemId}")
final class DecisionProcessController {

	GdmProblemService service;

	@RequestMapping(value = "/vote", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public void getProblem(@PathVariable long problemId,
			@RequestBody MultiValueMap<String, String> body) {

	}

}
