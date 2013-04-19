package com.tdm.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.tdm.domain.model.problem.dto.Problem;

@GenEvent
public class NewGdmProblem {

	Problem createdProblem;
}
