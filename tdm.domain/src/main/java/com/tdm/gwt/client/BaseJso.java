package com.tdm.gwt.client;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class BaseJso extends JavaScriptObject {

	protected BaseJso() {
	}

	public static final native <T extends BaseJso> T get(String json) /*-{
																				return eval('(' + json + ')');
																				}-*/;
}
