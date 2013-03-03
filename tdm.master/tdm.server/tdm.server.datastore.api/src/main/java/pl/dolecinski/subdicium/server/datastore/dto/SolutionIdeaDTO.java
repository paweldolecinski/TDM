package pl.dolecinski.subdicium.server.datastore.dto;

import java.util.Date;

public class SolutionIdeaDTO {

	private long id;
	private long problemId;
	private String name;
	private String details;
	private Date creation;

	public SolutionIdeaDTO(long id, long problemId, String solutionIdeaName) {
		this.id = id;
		this.problemId = problemId;
		name = solutionIdeaName;
	}

	public long getId() {
		return id;
	}

	public long getProblemId() {
		return problemId;
	}

	public String getName() {
		return name;
	}

	public String getDetails() {
		return details;
	}

	public Date getCreation() {
		return creation;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setProblemId(long problemId) {
		this.problemId = problemId;
	}

	public void setName(String name) {
		this.name = name;
	}
}
