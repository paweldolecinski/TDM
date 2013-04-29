package com.tdm.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;
import com.tdm.domain.model.idea.dto.SolutionIdea;

@GenEvent
public class VoteOnSolution {

	@Order(1)
	SolutionIdea solution;
	
	@Order(2)
	int note;
}
