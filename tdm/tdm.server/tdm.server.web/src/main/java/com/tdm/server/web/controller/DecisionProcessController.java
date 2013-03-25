package com.tdm.server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/problem/{problemId}")
public final class DecisionProcessController {

	@Autowired
	public DecisionProcessController() {
	}

	@RequestMapping(value = "/vote", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public void getProblem(@PathVariable long problemId,
			@RequestBody MultiValueMap<String, String> body) {

	}

}
