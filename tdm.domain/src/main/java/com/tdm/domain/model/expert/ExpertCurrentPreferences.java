package com.tdm.domain.model.expert;

import com.tdm.domain.model.expert.vo.Expert;
import com.tdm.domain.model.preferences.vo.FuzzyPreferences;

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