package com.tdm.domain.model.expert.dto;

import com.tdm.domain.model.expert.dto.Expert;
import com.tdm.domain.model.preferences.dto.FuzzyPreferences;

public class ExpertCurrentPreferences {

    private Expert expertId;
    private FuzzyPreferences preferences;

    public ExpertCurrentPreferences(Expert expertId) {
	this.expertId = expertId;
    }

    public Expert getExpertId() {
	return expertId;
    }

    public FuzzyPreferences getPreferences() {
	return preferences;
    }

    public void setPreferences(FuzzyPreferences preferences) {
	this.preferences = preferences;
    }

}
