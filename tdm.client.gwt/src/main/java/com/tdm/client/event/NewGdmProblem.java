package com.tdm.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.tdm.domain.model.problem.vo.GdmProblem;

@GenEvent
public class NewGdmProblem {

	GdmProblem createdProblem;
}
