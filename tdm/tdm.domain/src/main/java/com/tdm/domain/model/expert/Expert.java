package com.tdm.domain.model.expert;

public class Expert {

	private final ExpertId id;
	private ExpertRole role;
	private ExpertCurrentPreferences currentPreferences;

	public Expert(ExpertId id, ExpertRole role) {
		this.id = id;
		this.role = role;
	}

	public ExpertId getId() {
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
