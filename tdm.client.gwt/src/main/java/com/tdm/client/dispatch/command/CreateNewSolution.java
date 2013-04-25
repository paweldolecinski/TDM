package com.tdm.client.dispatch.command;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.tdm.domain.model.idea.dto.SolutionIdea;

@GenDispatch(isSecure = true)
public class CreateNewSolution {

	@In(1)
	String problemId;
	@In(2)
	SolutionIdea solutionIdea;
	
	@Out(1)
	SolutionIdea createdSolutionIdea;

}
