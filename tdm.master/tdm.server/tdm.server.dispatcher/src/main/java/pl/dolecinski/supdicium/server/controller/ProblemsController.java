package pl.dolecinski.supdicium.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.server.logic.model.Problem;
import com.tdm.server.logic.requests.ProblemService;


@Controller
@RequestMapping("/problems")
final class ProblemsController {

	ProblemService service;

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Problem getProblem(@PathVariable long id) {

		return service.getProblemInfo(id);
	}

}
