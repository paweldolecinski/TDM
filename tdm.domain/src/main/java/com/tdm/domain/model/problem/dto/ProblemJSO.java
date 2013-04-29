package com.tdm.domain.model.problem.dto;

import java.util.Date;

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
	public final native CurrentConsensus getCurrentConsensus() /*-{
																			return (this.currentConsensus == null) ? null : this.currentConsensus;
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
