package com.tdm.domain.model.expert;

import com.tdm.domain.model.preferences.IdeaPreference;

public class FuzzyPreferences {

	private Expert expertId;
	private IdeaPreference preferences;

	public FuzzyPreferences(Expert expertId) {
		this.expertId = expertId;
	}

	public Expert getExpertId() {
		return expertId;
	}

	public IdeaPreference getPreferences() {
		return preferences;
	}

	public void setPreferences(IdeaPreference preferences) {
		this.preferences = preferences;
	}

}
