package com.tdm.client.dispatch.handler;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.CreateGdmProblemAction;
import com.tdm.client.dispatch.command.CreateGdmProblemResult;
import com.tdm.client.util.UrlBuilder;
import com.tdm.domain.model.problem.jso.GdmProblemJso;

public class CreateGdmProblemHandler
	extends
	AbstractRequestBuilderClientActionHandler<CreateGdmProblemAction, CreateGdmProblemResult> {

    protected CreateGdmProblemHandler() {
	super(CreateGdmProblemAction.class);
    }

    @Override
    protected CreateGdmProblemResult extractResult(Response response) {
	JSONValue jsonValue = JSONParser.parseStrict(response.getText());
	JSONObject jsonObject = jsonValue.isObject(); // assert that this is an
						      // object
	GdmProblemJso items = jsonObject.getJavaScriptObject().cast();
	return new CreateGdmProblemResult(items);
    }

    @Override
    protected RequestBuilder getRequestBuilder(CreateGdmProblemAction action) {
	UrlBuilder urlBuilder = new UrlBuilder().setModule("api").setVersion(
		"v1");

	urlBuilder.addResourcePath("problems");
	urlBuilder.addQueryParameter("format", "json");
	RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,
		urlBuilder.toUrl());

	JSONObject jsonObject = new JSONObject(action.getNewProblem());

	String string = jsonObject.toString();
	rb.setRequestData(string);
	return rb;
    }

}
