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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.GetProblemListAction;
import com.tdm.client.dispatch.command.GetProblemListResult;
import com.tdm.client.util.UrlBuilder;
import com.tdm.domain.model.problem.dto.ProblemJSO;

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
		JsArray<ProblemJSO> problems = JsonUtils
				.safeEval(response.getText());
		return new GetProblemListResult(problems);
	}

	@Override
	protected RequestBuilder getRequestBuilder(
			final GetProblemListAction action, UrlBuilder urlBuilder) {

		urlBuilder.addResourcePath("search/problems");
		return prepareRequestBuilder(RequestBuilder.GET, urlBuilder);

	}

}
