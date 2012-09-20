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
package pl.dolecinski.supdicium.client.dispatch;

import com.google.gwt.http.client.Request;
import com.gwtplatform.dispatch.shared.DispatchRequest;

/**
 * @author Paweł Doleciński
 *
 */
public class DispatchRequestRequestBuilderImpl implements DispatchRequest {

	private final Request request;

	/**
	 * @param send
	 */
	public DispatchRequestRequestBuilderImpl(Request request) {
		super();
        this.request = request;
	}

	/* (non-Javadoc)
	 * @see com.gwtplatform.dispatch.shared.DispatchRequest#cancel()
	 */
	@Override
	public void cancel() {
		request.cancel();
	}

	/* (non-Javadoc)
	 * @see com.gwtplatform.dispatch.shared.DispatchRequest#isPending()
	 */
	@Override
	public boolean isPending() {
		return request.isPending();
	}

}
