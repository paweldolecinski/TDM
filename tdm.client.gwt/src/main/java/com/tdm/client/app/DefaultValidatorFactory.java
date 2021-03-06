package com.tdm.client.app;

import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import com.tdm.domain.model.problem.dto.ProblemJSO;

public class DefaultValidatorFactory extends AbstractGwtValidatorFactory {

	@GwtValidation(ProblemJSO.class)
	public interface GwtValidator extends Validator {
	}

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}

}
