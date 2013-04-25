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
package com.tdm.domain.model.expert;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.tdm.domain.model.problem.Problem;

/**
 * @author Paweł Doleciński
 */
@PersistenceCapable(detachable = "true")
public class Expert implements Serializable {

	private static final long serialVersionUID = -7951708410379553053L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	private String userId;
	private ExpertRole role = ExpertRole.MEMBER;
	private Problem problem;

	// private transient ExpertCurrentPreferences currentPreferences;

	public Expert(String id, ExpertRole role) {
		this.userId = id;
		this.role = role;
	}

	public String getId() {
		return userId;
	}

	public ExpertRole getRole() {
		return role;
	}

	public void setRole(ExpertRole role) {
		this.role = role;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public ExpertCurrentPreferences getCurrentPreferences() {
		return null;
	}

	public void setCurrentPreferences(
			ExpertCurrentPreferences currentPreferences) {
		// this.currentPreferences = currentPreferences;
	}
}
