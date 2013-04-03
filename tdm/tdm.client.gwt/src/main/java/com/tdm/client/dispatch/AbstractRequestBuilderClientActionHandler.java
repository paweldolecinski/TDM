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
package com.tdm.client.dispatch;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.client.actionhandler.AbstractClientActionHandler;
import com.gwtplatform.dispatch.client.actionhandler.ExecuteCommand;
import com.gwtplatform.dispatch.client.actionhandler.UndoCommand;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.DispatchRequest;
import com.gwtplatform.dispatch.shared.Result;

/**
 * Implementation of the command pattern using GWT's RequestBuilder instead of
 * GWT-RPC.
 * 
 * Use this DispatchAsync implementation when the backend is not GWT-RPC.
 * 
 * How to use: For each MyAction/MyActionResult pair: Implement a
 * AbstractRequestBuilderClientActionHandler<MyAction, MyActionResult> and
 * implement getRequestBuilder() and extractResult() abstract methods. Then
 * register your Handler in GIN, see : {@linkplain http
 * ://code.google.com/p/gwt-platform/wiki/ClientActionHandlers}
 * 
 * @author Paweł Doleciński
 * 
 */
public abstract class AbstractRequestBuilderClientActionHandler<A extends Action<R>, R extends Result>
		extends AbstractClientActionHandler<A, R> {

	protected AbstractRequestBuilderClientActionHandler(
			final Class<A> actionClass) {
		super(actionClass);
	}

	@Override
	public DispatchRequest execute(final A action,
			final AsyncCallback<R> resultCallback,
			final ExecuteCommand<A, R> executeCommand) {

		final RequestBuilder requestBuilder = getRequestBuilder(action);
		requestBuilder.setHeader("Content-Type", "application/json");
		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onError(final Request request, final Throwable exception) {
				resultCallback.onFailure(exception);
			}

			@Override
			public void onResponseReceived(final Request request,
					final Response response) {
				// TODO handle more errors, such as response.getStatusCode /
				// getStatusText
				resultCallback.onSuccess(extractResult(response));
			}
		});

		try {
			return new DispatchRequestRequestBuilderImpl(requestBuilder.send());
		} catch (final RequestException e) {
			throw new RequestRuntimeException(e);
		}
	}

	protected abstract R extractResult(Response response);

	protected abstract RequestBuilder getRequestBuilder(A action);

	@Override
	public DispatchRequest undo(final A action, final R result,
			final AsyncCallback<Void> callback,
			final UndoCommand<A, R> undoCommand) {
		throw new UnsupportedOperationException();
	}
}
