package com.tdm.domain.model.expert.dto;


public class ExpertDTO implements Expert {
	
	private final String userId;
	private ExpertRole role;
	private ExpertCurrentPreferences currentPreferences;

	public ExpertDTO(String userId, ExpertRole role) {
		this.userId = userId;
		this.role = role;
	}

	public String getUserId() {
		return userId;
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
