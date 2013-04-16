package com.tdm.domain.model.expert.vo.dto;

import com.tdm.domain.model.expert.ExpertCurrentPreferences;
import com.tdm.domain.model.expert.vo.Expert;
import com.tdm.domain.model.expert.vo.ExpertId;
import com.tdm.domain.model.expert.vo.ExpertRole;

public class ExpertDto implements Expert {
	
	private final ExpertId id;
	private ExpertRole role;
	private ExpertCurrentPreferences currentPreferences;

	public ExpertDto(ExpertId id, ExpertRole role) {
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
