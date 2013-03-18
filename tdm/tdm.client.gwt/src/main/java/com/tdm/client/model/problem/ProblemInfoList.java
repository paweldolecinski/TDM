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
package com.tdm.client.model.problem;

import java.util.List;

import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author Paweł Doleciński
 *
 */
public class ProblemInfoList {

	public interface ItemsReader extends JsonReader<ProblemInfoList> {
	}

	public static final ItemsReader JSON = GWT.create(ItemsReader.class);
	
	private List<ProblemInfo> problems;
	
	public List<ProblemInfo> getProblems() {
		return problems;
	}
}
