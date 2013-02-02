package pl.dolecinski.supdicium.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.requests.GdmProblemService;

@Controller
@RequestMapping("/problems")
final class ProblemController {

	GdmProblemService service;

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public GdmProblem getProblem(@PathVariable long id) {

		return service.retrieveProblem(GdmProblemId.create(id));
	}

}
