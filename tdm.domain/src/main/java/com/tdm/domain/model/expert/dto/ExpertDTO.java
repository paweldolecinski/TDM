package com.tdm.domain.model.expert.dto;


public class ExpertDTO implements Expert {
	
	private final String id;
	private ExpertRole role;
	private ExpertCurrentPreferences currentPreferences;

	public ExpertDTO(String id, ExpertRole role) {
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
