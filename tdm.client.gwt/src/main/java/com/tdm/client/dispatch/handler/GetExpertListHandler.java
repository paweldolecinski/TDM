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
import com.tdm.client.dispatch.command.GetExpertListAction;
import com.tdm.client.dispatch.command.GetExpertListResult;
import com.tdm.domain.model.expert.dto.ExpertJso;

/**
 * @author Paweł Doleciński
 * 
 */
public class GetExpertListHandler
		extends
		AbstractRequestBuilderClientActionHandler<GetExpertListAction, GetExpertListResult> {

	private static final String[] restResourcePath = { "problems",
			"{problemId}", "experts" };

	protected GetExpertListHandler() {
		super(GetExpertListAction.class);
	}

	@Override
	protected GetExpertListResult extractResult(final Response response) {
		JsArray<ExpertJso> res = JsonUtils.safeEval(response.getText());
		return new GetExpertListResult(res);
	}

	@Override
	protected RequestBuilder getRequestBuilder(
			final GetExpertListAction action) {

		restResourcePath[1] = action.getProblemId();

		return prepareRequestBuilder(RequestBuilder.GET, restResourcePath);

	}

}
