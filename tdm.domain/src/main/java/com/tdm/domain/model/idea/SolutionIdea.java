package com.tdm.domain.model.idea;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class SolutionIdea implements Serializable {

	private static final long serialVersionUID = 1193494744874575714L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	private Date creationDate = new Date();
	private String name;
	private String details;

	private Key problemId;

	public Key getKey() {
		return key;
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

	public Key getProblemId() {
		return problemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setProblem(Key problemId) {
		this.problemId = problemId;
	}
}