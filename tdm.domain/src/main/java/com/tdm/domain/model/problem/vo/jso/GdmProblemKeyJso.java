package com.tdm.domain.model.problem.vo.jso;

import com.tdm.domain.model.problem.vo.GdmProblemKey;
import com.tdm.gwt.client.BaseJso;

public class GdmProblemKeyJso extends BaseJso implements GdmProblemKey {

	protected GdmProblemKeyJso() {
	}

	@Override
	public final native String getId() /*-{
												return this.id;
												}-*/;

}
