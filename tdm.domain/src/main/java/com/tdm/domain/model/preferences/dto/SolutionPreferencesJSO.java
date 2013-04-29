package com.tdm.domain.model.preferences.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.tdm.gwt.client.BaseJso;

public class SolutionPreferencesJSO extends BaseJso implements
		SolutionPreferences {

	public static native SolutionPreferencesJSO create(String problemId,JsArray<NoteJSO> notes ) /*-{
		return {problemId: problemId, notes: notes};
	}-*/;

	protected SolutionPreferencesJSO() {
	}

	public final native String getProblemId() /*-{
		return this.problemId;
	}-*/;


	public final List<Note> getNotes() {
		ArrayList<Note> res = new ArrayList<Note>();
		JsArray<NoteJSO> nativeNotes = getNativeNotes();
		for (int i = 0; i < nativeNotes.length(); i++) {
			res.add(nativeNotes.get(i));
		}
		return res;
	}
	
	public final native JsArray<NoteJSO> getNativeNotes() /*-{
		return this.notes;
	}-*/;

	
	public static class NoteJSO extends BaseJso implements Note {
		
		public static native NoteJSO create(String solutionId, int note ) /*-{
			return {solutionId: solutionId, note: note};
		}-*/;
		protected NoteJSO() {
		}

		public final native String getSolutionId() /*-{
			return this.solutionId;
		}-*/;


		@Override
		public final native int getNote() /*-{
			return this.note;
		}-*/;

	}

}
