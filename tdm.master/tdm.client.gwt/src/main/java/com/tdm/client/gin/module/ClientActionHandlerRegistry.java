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
package com.tdm.client.gin.module;


import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.actionhandler.DefaultClientActionHandlerRegistry;
import com.tdm.client.dispatch.handler.GetProblemListHandler;

public class ClientActionHandlerRegistry extends
		DefaultClientActionHandlerRegistry {

	@Inject
	public ClientActionHandlerRegistry(
			final GetProblemListHandler getProblemListHandler) {

		register(getProblemListHandler);
	}
}