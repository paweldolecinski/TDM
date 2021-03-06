package com.tdm.domain.model.expert.dto;

import com.tdm.domain.model.preferences.dto.SolutionPreferences;

public class ExpertCurrentPreferences {

    private Expert expertId;
    private SolutionPreferences preferences;

    public ExpertCurrentPreferences(Expert expertId) {
	this.expertId = expertId;
    }

    public Expert getExpertId() {
	return expertId;
    }

    public SolutionPreferences getPreferences() {
	return preferences;
    }

    public void setPreferences(SolutionPreferences preferences) {
	this.preferences = preferences;
    }

}
