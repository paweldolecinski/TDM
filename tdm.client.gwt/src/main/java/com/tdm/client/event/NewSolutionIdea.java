package com.tdm.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.tdm.domain.model.idea.dto.SolutionIdea;

@GenEvent
public class NewSolutionIdea {

	SolutionIdea createdSolutionIdea;
}
