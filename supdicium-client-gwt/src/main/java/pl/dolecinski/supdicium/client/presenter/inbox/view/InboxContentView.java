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

import java.util.HashMap;

import pl.dolecinski.supdicium.client.SDStyle;
import pl.dolecinski.supdicium.client.model.problem.ProblemInfo;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxPagePresenter;
import pl.dolecinski.supdicium.client.theme.base.MainCss;
import pl.dolecinski.supdicium.client.ui.ProblemListItemWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author Paweł Doleciński
 * 
 */
public class InboxContentView extends ViewWithUiHandlers<ProblemListUiHandlers>
		implements InboxPagePresenter.Display {

	public interface Binder extends UiBinder<Widget, InboxContentView> {
	}

	private final Widget widget;

	@UiField
	protected HTMLPanel headerPanel;
	@UiField
	protected UListElement problemList;
	@UiField
	protected Button createButton;
	@UiField
	protected HeadingElement problemCounter;

	private HashMap<ProblemInfo, ProblemListItemWidget> problemWidgetMap = new HashMap<ProblemInfo, ProblemListItemWidget>();

	private Provider<ProblemListItemWidget> problemWidgetProvider;

	@Inject
	public InboxContentView(Binder binder, EventBus eventBus,
			Provider<ProblemListItemWidget> problemWidgetProvider) {
		widget = binder.createAndBindUi(this);
		this.problemWidgetProvider = problemWidgetProvider;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}

	@Override
	public void addProblemListItem(ProblemInfo problem) {
		LIElement liElement = Document.get().createLIElement();
		liElement.addClassName(getResources().span4());
		ProblemListItemWidget item = problemWidgetProvider.get();
		item.init(problem);
		problemWidgetMap.put(problem, item);
		liElement.appendChild(item.getElement());
		problemList.appendChild(liElement);
	}

	@Override
	public void problemCounter(int amount) {
		problemCounter.setInnerText("You have " + amount + " problems");
	}

	@Override
	public void clearProblemList() {
		while (problemList.getChildCount() > 2)
			problemList.removeChild(problemList.getLastChild());
	}

	@UiHandler("createButton")
	void handleClick(ClickEvent e) {
		Window.alert("TODO: New problem");
	}
}
