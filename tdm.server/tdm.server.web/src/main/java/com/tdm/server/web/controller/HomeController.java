package com.tdm.server.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.domain.model.problem.ProblemId;
import com.tdm.server.application.Broadcaster;

@Controller
public final class HomeController {

	@Autowired
	Broadcaster broadcaster;

	@RequestMapping(value = "/channel/{problemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getToken(@PathVariable String problemId,
			HttpServletResponse httpResponse_p, Principal principal) {
		String userId = principal.getName();

		String token = broadcaster.subscribe(new ProblemId(problemId), userId);

		httpResponse_p.setStatus(HttpStatus.CREATED.value());
		return token;
	}

}
