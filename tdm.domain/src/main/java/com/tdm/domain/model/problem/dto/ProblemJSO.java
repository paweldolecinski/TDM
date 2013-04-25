package com.tdm.domain.model.problem.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.JsArray;
import com.tdm.domain.model.expert.dto.Expert;
import com.tdm.domain.model.expert.dto.ExpertJso;
import com.tdm.gwt.client.BaseJso;

public class ProblemJSO extends BaseJso implements Problem {

	protected ProblemJSO() {
	}

	public static native ProblemJSO create(String name, String description) /*-{
																				return {name: name, description: description};
																				}-*/;

	public final native String getKey() /*-{
												return this.key;
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
		return new Date((long) getCreationDateTimestamp());
	}

	private final native double getCreationDateTimestamp() /*-{
															return this.creationDate;
															}-*/;

	@Override
	public final Set<Expert> getExperts() {
		JsArray<ExpertJso> jsoExperts = getJsoExperts();
		Set<Expert> res = new HashSet<Expert>();
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
																			return (this.currentConsensus == null) ? null : this.currentConsensus;
																			}-*/;

	@Override
	public final native void addExpert(Expert expert) /*-{
																			this.experts[this.experts.length] = value;
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
