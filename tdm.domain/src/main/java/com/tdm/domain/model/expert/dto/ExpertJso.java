package com.tdm.domain.model.expert.dto;

import com.tdm.gwt.client.BaseJso;

public class ExpertJso extends BaseJso implements Expert {

	protected ExpertJso() {
	}

	public native final String getUserId() /*-{
											return this.userId;
											}-*/;

	public final ExpertRole getRole() {
		return ExpertRole.valueOf(getNativeExpertRole());
	}

	public final void setRole(ExpertRole expertRole) {
		setNativeExpertRole(expertRole.name());
	}

	public final native String getNativeExpertRole() /*-{
												return this.role;
												}-*/;

	public final native void setNativeExpertRole(final String expertRole) /*-{
																		this.role = role;
																		}-*/;
}
