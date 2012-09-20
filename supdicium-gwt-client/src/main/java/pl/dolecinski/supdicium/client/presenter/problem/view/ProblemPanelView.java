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
package pl.dolecinski.supdicium.client.presenter.problem.view;

import pl.dolecinski.supdicium.client.SDStyle;
import pl.dolecinski.supdicium.client.presenter.problem.ProblemDicussionPresenter;
import pl.dolecinski.supdicium.client.theme.base.MainCss;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemPanelView extends ViewImpl implements
		ProblemDicussionPresenter.Display {

	public interface Binder extends UiBinder<Widget, ProblemPanelView> {
	}

	private final Widget widget;

	@UiField
	FlowPanel brainstormPanel;

	@UiField
	FlowPanel newSolutionPanel;
	@UiField
	HTMLPanel problemDescription;
	@UiField
	DeckPanel deckPanel;
	
	@Inject
	public ProblemPanelView(Binder binder, EventBus eventBus) {
		widget = binder.createAndBindUi(this);
		deckPanel.setAnimationEnabled(true);
		deckPanel.showWidget(0);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == ProblemDicussionPresenter.TYPE_NewSolution) {
			setInNewSolutionSlot(content);
		} else if (slot == ProblemDicussionPresenter.TYPE_Brainstorm) {
			setInBrainstormSlot(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	/**
	 * @param content
	 */
	private void setInBrainstormSlot(Widget content) {
		brainstormPanel.clear();
		if (content != null) {
			brainstormPanel.add(content);
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

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}
}
