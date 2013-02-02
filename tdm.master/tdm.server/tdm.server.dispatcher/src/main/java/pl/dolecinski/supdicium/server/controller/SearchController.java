package pl.dolecinski.supdicium.server.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.requests.GdmProblemService;

@Controller
@RequestMapping("/search")
final class SearchController {

	GdmProblemService service;

	@RequestMapping(value = "/problems.json/{result_type}/{q}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Set<GdmProblem> getProblem(@PathVariable String result_type,
			@PathVariable String q) {

		return Collections.<GdmProblem> emptySet();
	}

}
