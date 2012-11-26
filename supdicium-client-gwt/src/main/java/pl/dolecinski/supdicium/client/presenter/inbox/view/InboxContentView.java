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
package pl.dolecinski.supdicium.client.presenter.inbox.view;

import pl.dolecinski.supdicium.client.SDStyle;
import pl.dolecinski.supdicium.client.model.problem.ProblemInfo;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxPagePresenter;
import pl.dolecinski.supdicium.client.presenter.inbox.view.InboxContentView.ProblemListUiHandlers;
import pl.dolecinski.supdicium.client.theme.base.MainCss;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author Paweł Doleciński
 * 
 */
public class InboxContentView extends ViewWithUiHandlers<ProblemListUiHandlers> implements
		InboxPagePresenter.Display {

	public interface ProblemListUiHandlers extends UiHandlers {
		void showDecisionProblem(String problemId);

		void refreshProblemList(String filter);
	}

	public interface Binder extends UiBinder<Widget, InboxContentView> {
	}

	private final Widget widget;

	@UiField
	protected HTMLPanel headerPanel;
	@UiField
	protected HTMLPanel problemListPanel;

	@Inject
	public InboxContentView(Binder binder, EventBus eventBus) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == InboxPagePresenter.TYPE_InboxMenu) {
			setInboxMenu(content);
		} else if (slot == InboxPagePresenter.TYPE_ProblemList) {
			setProblemList(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setInboxMenu(Widget content) {
		headerPanel.clear();
		headerPanel.setVisible(true);
		if (content != null) {
			headerPanel.add(content);
		}
	}

	private void setProblemList(Widget content) {
		problemListPanel.clear();
		problemListPanel.setVisible(true);
		if (content != null) {
			problemListPanel.add(content);
		}
	}

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}

	@Override
	public ListDataProvider<ProblemInfo> getProblemsDataProvider() {
		// TODO Auto-generated method stub
		return null;
	}
}
