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

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author Paweł Doleciński
 */
@PersistenceCapable
public class Expert {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	private final String id;
	private ExpertRole role;
	private ExpertCurrentPreferences currentPreferences;

	public Expert(String id, ExpertRole role) {
		this.id = id;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public ExpertRole getRole() {
		return role;
	}

	public void setRole(ExpertRole role) {
		this.role = role;
	}

	public ExpertCurrentPreferences getCurrentPreferences() {
		return currentPreferences;
	}

	public void setCurrentPreferences(
			ExpertCurrentPreferences currentPreferences) {
		this.currentPreferences = currentPreferences;
	}
}
