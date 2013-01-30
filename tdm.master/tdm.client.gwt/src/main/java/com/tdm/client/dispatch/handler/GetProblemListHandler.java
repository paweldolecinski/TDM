/**
 * Copyright 2011 Paweł Doleciński.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tdm.client.dispatch.handler;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.GetProblemListAction;
import com.tdm.client.dispatch.command.GetProblemListResult;
import com.tdm.client.model.problem.ProblemInfoList;
import com.tdm.client.util.UrlBuilder;

/**
 * @author Paweł Doleciński
 * 
 */
public class GetProblemListHandler
		extends
		AbstractRequestBuilderClientActionHandler<GetProblemListAction, GetProblemListResult> {

	protected GetProblemListHandler() {
		super(GetProblemListAction.class);
	}

	@Override
	protected GetProblemListResult extractResult(final Response response) {
		System.out.println(response.getText());
		final ProblemInfoList items = ProblemInfoList.JSON.read(response
				.getText());
		return new GetProblemListResult(items);
	}

	@Override
	protected RequestBuilder getRequestBuilder(final GetProblemListAction action) {
		UrlBuilder urlBuilder = new UrlBuilder().setModule("api").setVersion(
				"v1");

		urlBuilder.addResourcePath("problems/" + action.getFilter());

		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET,
				urlBuilder.toUrl());

		return rb;
	}

}
