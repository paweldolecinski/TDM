package com.tdm.domain.model.preferences.dto;

import java.util.List;

public interface SolutionPreferences {

	String getProblemId();
	
	List<? extends Note> getNotes();
	
	interface Note {
		String getSolutionId();
		int getNote();
	}
}
