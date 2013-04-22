package com.tdm.client.dispatch.handler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.CreateNewSolutionAction;
import com.tdm.client.dispatch.command.CreateNewSolutionResult;
import com.tdm.domain.model.idea.dto.SolutionIdea;
import com.tdm.domain.model.idea.dto.SolutionIdeaJSO;

public class CreateNewSolutionHandler
		extends
		AbstractRequestBuilderClientActionHandler<CreateNewSolutionAction, CreateNewSolutionResult> {

	private static final String[] restResourcePath = { "problems",
			"{problemId}", "ideas" };

	protected CreateNewSolutionHandler() {
		super(CreateNewSolutionAction.class);
	}

	@Override
	protected CreateNewSolutionResult extractResult(Response response) {
		SolutionIdeaJSO created = JsonUtils.safeEval(response.getText());
		return new CreateNewSolutionResult(created);
	}

	@Override
	protected RequestBuilder getRequestBuilder(CreateNewSolutionAction action) {

		SolutionIdea solutionIdea = action.getSolutionIdea();
		restResourcePath[1] = solutionIdea.getProblemId();

		JSONObject jsonObject = new JSONObject((JavaScriptObject) solutionIdea);

		return prepareRequestBuilder(RequestBuilder.POST, restResourcePath,
				jsonObject);
	}

}
