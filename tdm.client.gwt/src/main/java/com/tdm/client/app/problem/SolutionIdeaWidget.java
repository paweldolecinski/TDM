/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.tdm.client.app.problem;

import java.util.Date;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.base.InlineLabel;
import com.github.gwtbootstrap.client.ui.base.ListItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.tdm.client.event.VoteOnSolutionEvent;
import com.tdm.domain.model.idea.dto.SolutionIdea;

/**
 * The view implementation for
 * {@link com.gwtplatform.SolutionIdeaPresenterWidget.tab.client.application.localdialog.LocalDialogPresenterWidget}
 * .
 */
public class SolutionIdeaWidget extends Composite {

	public static class Provider implements
			com.google.inject.Provider<SolutionIdeaWidget> {

		private EventBus eventBus;

		@Inject
		public Provider(EventBus eventBus) {
			this.eventBus = eventBus;

		}

		@Override
		public SolutionIdeaWidget get() {
			return new SolutionIdeaWidget(eventBus);
		}
	}

	public interface Binder extends UiBinder<ListItem, SolutionIdeaWidget> {
	}

	private static Binder binder = GWT.create(Binder.class);

	@UiField
	protected InlineLabel titleText;
	@UiField
	protected Paragraph detailsText;

	private SolutionIdea solutionIdea;
	private EventBus eventBus;

	public SolutionIdeaWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		initWidget(binder.createAndBindUi(this));
		detailsText.setText(new Date().toString());
	}

	public void init(SolutionIdea solutionIdea) {
		this.solutionIdea = solutionIdea;
		titleText.setText(solutionIdea.getName());
		detailsText.setText(solutionIdea.getDetails());
	}

	@UiHandler({ "vote0", "vote1", "vote2", "vote3", "vote4", "vote5", "vote6", "vote7",
			"vote8", "vote9", "vote10" })
	void onClickShowModal(ClickEvent event) {
		Button b = (Button) event.getSource();
		String text = b.getTitle();
		int note = Integer.parseInt(text);
		eventBus.fireEvent(new VoteOnSolutionEvent(solutionIdea, note));

	}

	public SolutionIdea getSolutionIdea() {
		return solutionIdea;
	}
}
