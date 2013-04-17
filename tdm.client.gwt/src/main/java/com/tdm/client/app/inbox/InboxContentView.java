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
package com.tdm.client.app.inbox;

import java.util.HashMap;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.tdm.client.app.ui.ProblemListItemWidget;
import com.tdm.client.resources.AppResources;
import com.tdm.domain.model.problem.vo.GdmProblem;

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
	@UiField
	protected LIElement createItem;

	private HashMap<GdmProblem, ProblemListItemWidget> problemWidgetMap = new HashMap<GdmProblem, ProblemListItemWidget>();

	private Provider<ProblemListItemWidget> problemWidgetProvider;

	private AppResources resources;

	@Inject
	public InboxContentView(Binder binder, EventBus eventBus,
			Provider<ProblemListItemWidget> problemWidgetProvider,
			AppResources resources) {
		this.resources = resources;
		widget = binder.createAndBindUi(this);
		this.problemWidgetProvider = problemWidgetProvider;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void addProblemListItem(GdmProblem problem) {
		LIElement liElement = Document.get().createLIElement();
		liElement.addClassName(resources.mainCss().span4());
		ProblemListItemWidget item = problemWidgetProvider.get();
		item.init(problem);
		problemWidgetMap.put(problem, item);
		liElement.appendChild(item.getElement());
		problemList.insertAfter(liElement, createItem);
		problemCounter.setInnerText("You have " + problemList.getChildCount()
				+ " problems");
	}

	@Override
	public void clearProblemList() {
		while (problemList.getChildCount() > 2)
			problemList.removeChild(problemList.getLastChild());
	}

	@UiHandler("createButton")
	void handleClick(ClickEvent e) {
		getUiHandlers().createNewProblem();
	}
}