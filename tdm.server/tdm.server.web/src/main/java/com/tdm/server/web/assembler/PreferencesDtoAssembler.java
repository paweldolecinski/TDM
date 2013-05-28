package com.tdm.server.web.assembler;

import com.tdm.domain.model.preferences.SolutionPreferences;
import com.tdm.domain.model.preferences.dto.SolutionPreferencesDTO;

public class PreferencesDtoAssembler {

	public SolutionPreferencesDTO fromEntity(SolutionPreferences solutionPreferences) {
		SolutionPreferencesDTO dto = new SolutionPreferencesDTO();
		return dto;
	}

	public SolutionPreferences toEntity(SolutionPreferencesDTO solutionPreferences) {
		SolutionPreferences entity = new SolutionPreferences();

		return entity;
	}

}
