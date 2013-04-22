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

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.idea.SolutionIdea;

/**
 * @author Paweł Doleciński
 */
@PersistenceCapable
public class Problem {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String encodedKey;

	@Persistent
	private Date creationDate = new Date();

	@Persistent
	private String name;

	@Persistent
	private String description;

	@Persistent
	private Set<Expert> experts;

	@Persistent
	private CurrentConsensus currentConsensus;

	@Persistent(mappedBy = "problem")
	@Element(dependent = "true")
	@Order(extensions = @Extension(vendorName = "datanucleus", key = "list-ordering", value = "creationDate asc"))
	private List<SolutionIdea> solutionIdeas;

	public String getEncodedKey() {
		return encodedKey;
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
	}

	public CurrentConsensus getCurrentConsensus() {
		return currentConsensus;
	}

	public void setCurrentConsensus(CurrentConsensus currentConsensus) {
		this.currentConsensus = currentConsensus;
	}

	public List<SolutionIdea> getSolutionIdeas() {
		return solutionIdeas;
	}

	public void addSolutionIdeas(SolutionIdea solutionIdea) {
		this.solutionIdeas.add(solutionIdea);
	}

	public void setSolutionIdeas(List<SolutionIdea> solutionIdeas) {
		this.solutionIdeas = solutionIdeas;
	}
}