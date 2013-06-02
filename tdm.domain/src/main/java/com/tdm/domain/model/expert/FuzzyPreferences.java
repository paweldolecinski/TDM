package com.tdm.domain.model.expert;

import com.tdm.domain.model.preferences.IdeaPairPreferences;

public class FuzzyPreferences {

	private Expert expertId;
	private IdeaPairPreferences preferences;

	public FuzzyPreferences(Expert expertId) {
		this.expertId = expertId;
	}

	public Expert getExpertId() {
		return expertId;
	}

	public IdeaPairPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(IdeaPairPreferences preferences) {
		this.preferences = preferences;
	}

}
