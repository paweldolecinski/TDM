package com.tdm.domain.model.idea;

import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.tdm.domain.model.problem.Problem;

@PersistenceCapable(detachable = "true")
public class SolutionIdea {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String encodedKey;

	@Persistent
	private Date creationDate = new Date();

	@Persistent
	private String name;

	@Persistent
	private String details;

	@Persistent
	private Problem problem;

	public String getEncodedKey() {
		return encodedKey;
	}

	public String getName() {
		return name;
	}

	public String getDetails() {
		return details;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
}