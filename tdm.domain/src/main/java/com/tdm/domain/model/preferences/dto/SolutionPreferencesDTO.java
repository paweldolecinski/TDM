package com.tdm.domain.model.preferences.dto;

import java.util.List;

public class SolutionPreferencesDTO implements SolutionPreferences {

	private String problemId;
	private List<NoteDTO> notes;

	@Override
	public String getProblemId() {
		return problemId;
	}

	@Override
	public List<? extends Note> getNotes() {
		return notes;
	}

	public static class NoteDTO implements Note {

		private String solutionId;
		private int note;

		@Override
		public String getSolutionId() {
			return solutionId;
		}

		@Override
		public int getNote() {
			return note;
		}
	}

}
