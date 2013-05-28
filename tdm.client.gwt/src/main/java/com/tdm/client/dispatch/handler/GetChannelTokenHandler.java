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
import com.tdm.client.dispatch.command.GetChannelTokenAction;
import com.tdm.client.dispatch.command.GetChannelTokenResult;

/**
 * @author Paweł Doleciński
 * 
 */
public class GetChannelTokenHandler
		extends
		AbstractRequestBuilderClientActionHandler<GetChannelTokenAction, GetChannelTokenResult> {

	private static final String[] restResourcePath = { "channel", "{problemId}" };

	protected GetChannelTokenHandler() {
		super(GetChannelTokenAction.class);
	}

	@Override
	protected GetChannelTokenResult extractResult(final Response response) {
		String token = response.getText();
		return new GetChannelTokenResult(token);
	}

	@Override
	protected RequestBuilder getRequestBuilder(
			final GetChannelTokenAction action) {
		restResourcePath[1] = action.getProblemId();
		return prepareRequestBuilder(RequestBuilder.GET, restResourcePath);

	}

}
