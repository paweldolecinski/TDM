package com.tdm.domain.model.idea.dto;

import java.util.Date;

import com.tdm.gwt.client.BaseJso;

public class SolutionIdeaJSO extends BaseJso implements SolutionIdea {

	protected SolutionIdeaJSO() {
	}

	public static native SolutionIdeaJSO create(String name) /*-{
																					return {name: name};
																					}-*/;

	@Override
	public final native String getName() /*-{
											return this.name;
											}-*/;

	@Override
	public final native String getDetails() /*-{
												return this.description;
												}-*/;

	@Override
	public final Date getCreationDate() {
		return new Date((long) getCreationDateTimestamp());
	}

	private final native double getCreationDateTimestamp() /*-{
															return this.creationDate;
															}-*/;

	public final native String getId() /*-{
										return this.id;
										}-*/;

	public final native String getProblemId() /*-{
												return this.problemId;
												}-*/;

}
