package com.tdm.server.web.assembler;

import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.preferences.dto.SolutionPreferences.Note;
import com.tdm.domain.model.preferences.dto.SolutionPreferencesDTO;
import com.tdm.server.application.decision.preference.SolutionIdeaNote;
import com.tdm.server.application.decision.preference.SolutionIdeaUtilityList;

public class PreferencesDtoAssembler {

	public SolutionIdeaUtilityList toModel(SolutionPreferencesDTO preferences) {
		SolutionIdeaUtilityList model = new SolutionIdeaUtilityList();
		for (Note note : preferences.getNotes()) {
			model.add(new SolutionIdeaNote(new SolutionIdeaId(note
					.getSolutionId()), note.getNote()));
		}
		return model;
	}

}
