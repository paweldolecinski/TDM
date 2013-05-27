package com.tdm.server.application.problem.service;

import com.tdm.domain.model.handling.ObjectNotFoundException;

public class ProblemNotFoundException extends RuntimeException {

	public ProblemNotFoundException(String string) {
		super(string);
	}

	public ProblemNotFoundException(String string, ObjectNotFoundException ex) {
		super(string, ex);
	}

	private static final long serialVersionUID = 3319830880201933798L;

}
