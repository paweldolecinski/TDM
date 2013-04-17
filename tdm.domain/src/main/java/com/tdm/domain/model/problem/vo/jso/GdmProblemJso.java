package com.tdm.domain.model.problem.vo.jso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gwt.core.client.JsArray;
import com.tdm.domain.model.expert.vo.Expert;
import com.tdm.domain.model.expert.vo.jso.ExpertJso;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.GdmProblemCurrentConsensus;
import com.tdm.domain.model.problem.vo.GdmProblemKey;
import com.tdm.gwt.client.BaseJso;

public class GdmProblemJso extends BaseJso implements GdmProblem {

	protected GdmProblemJso() {
	}

	public static native GdmProblemJso create(String name, String description) /*-{
																				return {name: name, description: description};
																				}-*/;

	public final native GdmProblemKey getKey() /*-{
												return this.key;
												}-*/;

	@NotNull(message = "Title cannot be empty.")
	@Size(min = 1, message = "Title must be at least 1 character long.")
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
