/**
 * Copyright 2011 Paweł Doleciński.
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

import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewImpl;
import com.tdm.client.app.AppView;
import com.tdm.client.resources.AppResources;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemDiscussionView extends ViewImpl implements
		ProblemDicussionPresenter.Display {

	public interface Binder extends UiBinder<Widget, ProblemDiscussionView> {
	}

	private final Widget widget;

	@UiField
	UListElement solutionList;

	@UiField
	FlowPanel newSolutionPanel;

	private AppResources resources;

	// @UiField
	// HTMLPanel problemDescription;
	// @UiField
	// DeckPanel deckPanel;

	@Inject
	public ProblemDiscussionView(Binder binder, EventBus eventBus,
			AppResources resources) {
		this.resources = resources;
		widget = binder.createAndBindUi(this);
		// deckPanel.setAnimationEnabled(true);
		// deckPanel.showWidget(0);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == ProblemDicussionPresenter.TYPE_NewSolution) {
			setInNewSolutionSlot(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	/**
	 * @param content
	 */
	private void setInNewSolutionSlot(Widget content) {
		newSolutionPanel.clear();
		if (content != null) {
			newSolutionPanel.add(content);
		}
	}

	@Override
	public void onReveal() {
		AppView.getBody().setClassName(resources.getMainCss().landing());
	}

	@Override
	public void onHide() {
		AppView.getBody().removeClassName(resources.getMainCss().landing());
	}

}
