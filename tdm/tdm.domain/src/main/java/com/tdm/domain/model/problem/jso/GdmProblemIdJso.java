package com.tdm.domain.model.problem.jso;

import com.google.gwt.core.client.JavaScriptObject;
import com.tdm.domain.model.problem.GdmProblemId;

public class GdmProblemIdJso extends JavaScriptObject implements GdmProblemId {

    protected GdmProblemIdJso() {
    }

    @Override
    public final native String getIdString() /*-{
					     return this.id;
					     }-*/;

    public final native void setId(String id) /*-{
					      this.id = id;
					      }-*/;
}
