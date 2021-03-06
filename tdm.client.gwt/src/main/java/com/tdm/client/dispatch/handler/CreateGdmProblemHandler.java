package com.tdm.client.dispatch.handler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.CreateGdmProblemAction;
import com.tdm.client.dispatch.command.CreateGdmProblemResult;
import com.tdm.domain.model.problem.dto.ProblemJSO;

public class CreateGdmProblemHandler
		extends
		AbstractRequestBuilderClientActionHandler<CreateGdmProblemAction, CreateGdmProblemResult> {

	private static final String[] restResourcePath = { "problems" };

	protected CreateGdmProblemHandler() {
		super(CreateGdmProblemAction.class);
	}

	@Override
	protected CreateGdmProblemResult extractResult(Response response) {
		ProblemJSO createdProblem = JsonUtils.safeEval(response.getText());
		JSONObject jsonObject = new JSONObject(createdProblem);
		System.out.println(jsonObject.toString());
		return new CreateGdmProblemResult(createdProblem);
	}

	@Override
	protected RequestBuilder getRequestBuilder(CreateGdmProblemAction action) {

		JSONObject jsonObject = new JSONObject(
				(JavaScriptObject) action.getProblem());

		return prepareRequestBuilder(RequestBuilder.POST, restResourcePath,
				jsonObject);
	}

}
