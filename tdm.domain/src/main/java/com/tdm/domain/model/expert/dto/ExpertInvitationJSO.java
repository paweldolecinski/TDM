package com.tdm.domain.model.expert.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArrayString;
import com.tdm.gwt.client.BaseJso;

public class ExpertInvitationJSO extends BaseJso implements ExpertsInvitation {

	protected ExpertInvitationJSO() {
	}

	public static native ExpertInvitationJSO create(JsArrayString emails,
			String message) /*-{
								return {emails: emails, message: message};
								}-*/;

	@Override
	public final List<String> getEmails() {
		JsArrayString nativeEmails = getNativeEmails();
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < nativeEmails.length(); i++) {
			res.add(nativeEmails.get(i));
		}
		return res;
	}

	public final native JsArrayString getNativeEmails()/*-{
														return this.emails;
														}-*/;

	public final native String getMessage()/*-{
											return this.message;
											}-*/;

}
