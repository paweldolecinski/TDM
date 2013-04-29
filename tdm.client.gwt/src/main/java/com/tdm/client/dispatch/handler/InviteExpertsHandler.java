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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.tdm.client.dispatch.AbstractRequestBuilderClientActionHandler;
import com.tdm.client.dispatch.command.InviteExpertsAction;
import com.tdm.client.dispatch.command.InviteExpertsResult;

/**
 * @author Paweł Doleciński
 * 
 */
public class InviteExpertsHandler
		extends
		AbstractRequestBuilderClientActionHandler<InviteExpertsAction, InviteExpertsResult> {

	private static final String[] restResourcePath = { "decision",
			"{problemId}", "invite" };

	protected InviteExpertsHandler() {
		super(InviteExpertsAction.class);
	}

	@Override
	protected InviteExpertsResult extractResult(final Response response) {
		return new InviteExpertsResult();
	}

	@Override
	protected RequestBuilder getRequestBuilder(final InviteExpertsAction action) {

		restResourcePath[1] = action.getProblemId();
		JSONObject jsonObject = new JSONObject(
				(JavaScriptObject) action.getInvitation());
		return prepareRequestBuilder(RequestBuilder.GET, restResourcePath,
				jsonObject);

	}
}
