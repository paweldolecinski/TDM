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
package com.tdm.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Paweł Doleciński
 */
public class Problem {

	private long id;
	private String name;
	private String description;
	private Map<Long, String> assignedExperts = new HashMap<Long, String>();

	public Problem(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void addExpert(long expertId, String expertRole) {
		assignedExperts.put(expertId, expertRole);
	}

	public Map<Long, String> getAssignedExpertsWithRoles() {
		return assignedExperts;
	}

}
