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

import java.util.HashMap;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.GetProblemByIdAction;
import com.tdm.client.dispatch.command.GetProblemByIdResult;
import com.tdm.domain.model.problem.dto.ProblemJSO;

/**
 * @author Paweł Doleciński
 * 
 */
public class GetProblemByIdHandler
		extends
		AbstractRequestBuilderClientActionHandler<GetProblemByIdAction, GetProblemByIdResult> {

	private static final String[] restResourcePath = { "problems",
			"{problemId}" };

	protected GetProblemByIdHandler() {
		super(GetProblemByIdAction.class);
	}

	@Override
	protected GetProblemByIdResult extractResult(final Response response) {
		ProblemJSO problem = JsonUtils.safeEval(response.getText());
		return new GetProblemByIdResult(problem);
	}

	@Override
	protected RequestBuilder getRequestBuilder(final GetProblemByIdAction action) {

		restResourcePath[1] = action.getProblemId();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (action.isJoin()) {
			hashMap.put("join", "true");
		}
		return prepareRequestBuilder(RequestBuilder.GET, restResourcePath,
				hashMap);

	}

}
