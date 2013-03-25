package com.tdm.domain.model.expert;

import com.tdm.domain.model.preferences.FuzzyPreferenceRelation;

public class ExpertCurrentPreferences {

	private Expert expertId;
	private FuzzyPreferenceRelation preferences;

	public ExpertCurrentPreferences(Expert expertId) {
		this.expertId = expertId;
	}

	public Expert getExpertId() {
		return expertId;
	}

	public FuzzyPreferenceRelation getPreferences() {
		return preferences;
	}

	public void setPreferences(FuzzyPreferenceRelation preferences) {
		this.preferences = preferences;
	}

}
