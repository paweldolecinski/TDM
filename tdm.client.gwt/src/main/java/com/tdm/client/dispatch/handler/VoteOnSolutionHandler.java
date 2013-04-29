package com.tdm.client.dispatch.handler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.VoteOnSolutionAction;
import com.tdm.client.dispatch.command.VoteOnSolutionResult;
import com.tdm.domain.model.preferences.dto.SolutionPreferencesJSO;

public class VoteOnSolutionHandler
		extends
		AbstractRequestBuilderClientActionHandler<VoteOnSolutionAction, VoteOnSolutionResult> {

	private static final String[] restResourcePath = { "decision",
			"{problemId}", "vote" };

	protected VoteOnSolutionHandler() {
		super(VoteOnSolutionAction.class);
	}

	@Override
	protected VoteOnSolutionResult extractResult(Response response) {
		return new VoteOnSolutionResult();
	}

	@Override
	protected RequestBuilder getRequestBuilder(VoteOnSolutionAction action) {
		SolutionPreferencesJSO preferences = action.getPreferences();

		restResourcePath[1] = preferences.getProblemId();

		JSONObject jsonObject = new JSONObject((JavaScriptObject) preferences);

		return prepareRequestBuilder(RequestBuilder.POST, restResourcePath,
				jsonObject);
	}

}
