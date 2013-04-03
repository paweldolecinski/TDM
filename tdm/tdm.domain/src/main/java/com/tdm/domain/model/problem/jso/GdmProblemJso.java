package com.tdm.domain.model.problem.jso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.jso.ExpertJso;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.GdmProblemCurrentConsensus;
import com.tdm.domain.model.problem.GdmProblemId;

public class GdmProblemJso extends JavaScriptObject implements GdmProblem {

	private static final DateTimeFormat df = DateTimeFormat
			.getFormat("yyyyMMdd");

	protected GdmProblemJso() {
	}

	@Override
	public final native GdmProblemId getId() /*-{
												this.id;
												}-*/;

	@Override
	public final native String getName() /*-{
											return this.name;
											}-*/;

	@Override
	public final native String getDescription() /*-{
												return this.description;
												}-*/;

	@Override
	public final Date getCreationDate() {
		return df.parse(getCreationDateString());
	}

	private final native String getCreationDateString() /*-{
														return this.creationDate;
														}-*/;

	@Override
	public final List<Expert> getExperts() {
		JsArray<ExpertJso> jsoExperts = getJsoExperts();
		ArrayList<Expert> res = new ArrayList<Expert>();
		for (int i = 0; i < jsoExperts.length(); i++) {
			res.add(jsoExperts.get(i));
		}
		return res;
	}

	private final native JsArray<ExpertJso> getJsoExperts() /*-{
															return this.experts;
															}-*/;

	@Override
	public final native GdmProblemCurrentConsensus getCurrentConsensus() /*-{
																			(this.currentConsensus == null) ? null : this.currentConsensus;
																			}-*/;

	@Override
	public final native void addExpert(Expert expert) /*-{
																			this.experts[this.experts.length] = value;
																			}-*/;

	@Override
	public final native void setId(GdmProblemId id) /*-{
													this.id = id;
													}-*/;

	@Override
	public final native void setName(String name) /*-{
													this.name = name;
													}-*/;

	@Override
	public final native void setDescription(String description) /*-{
																this.description = description;
																}-*/;

}
