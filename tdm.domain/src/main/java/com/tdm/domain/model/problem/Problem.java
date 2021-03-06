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
package com.tdm.domain.model.problem;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.JDOHelper;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.tdm.domain.model.expert.Expert;

/**
 * @author Paweł Doleciński
 */
@PersistenceCapable(detachable = "true")
public class Problem implements Serializable {

	private static final long serialVersionUID = 4432277540027624062L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	private Date creationDate = new Date();
	private String name;
	private String description;

	@Element(dependent = "true")
	@Persistent(mappedBy = "problem")
	private Set<Expert> experts = new HashSet<Expert>();

	@Embedded
	private CurrentConsensus currentConsensus = new CurrentConsensus();

	public Key getKey() {
		return key;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Expert> getExperts() {
		return experts;
	}

	public void setExperts(Set<Expert> experts) {
		this.experts = experts;
		JDOHelper.makeDirty(this, "experts");
	}

	public CurrentConsensus getCurrentConsensus() {
		return currentConsensus;
	}

	public void setCurrentConsensus(CurrentConsensus currentConsensus) {
		this.currentConsensus = currentConsensus;
	}

	public void addExpert(Expert expert) {
		experts.add(expert);
	}
}