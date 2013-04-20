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
package com.tdm.client.app.home;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Thumbnails;
import com.google.gwt.dom.client.HeadingElement;
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
import com.tdm.domain.model.problem.dto.Problem;

/**
 * @author Paweł Doleciński
 * 
 */
public class HomeView extends ViewWithUiHandlers<ProblemListUiHandlers>
		implements HomePresenter.Display {

	public interface Binder extends UiBinder<Widget, HomeView> {
	}

	private final Widget widget;

	@UiField
	protected HTMLPanel headerPanel;
	@UiField
	protected Thumbnails problemList;
	@UiField
	protected Button createButton;
	@UiField
	protected HeadingElement problemCounter;

	private HashMap<Problem, ProblemListItemWidget> problemWidgetMap = new HashMap<Problem, ProblemListItemWidget>();

	private Provider<ProblemListItemWidget> problemWidgetProvider;

	@Inject
	public HomeView(Binder binder, EventBus eventBus,
			Provider<ProblemListItemWidget> problemWidgetProvider,
			AppResources resources) {
		widget = binder.createAndBindUi(this);
		this.problemWidgetProvider = problemWidgetProvider;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void addProblemListItem(Problem problem) {
		ProblemListItemWidget item = problemWidgetProvider.get();
		item.init(problem);
		problemWidgetMap.put(problem, item);
		problemList.add(item);

		int i = problemWidgetMap.size();
		problemCounter.setInnerText("You have " + i
				+ (i > 1 ? " problems" : " problem"));
	}

	@Override
	public void clearProblemList() {
		for (ProblemListItemWidget problemListItemWidget : problemWidgetMap
				.values()) {
			problemList.remove(problemListItemWidget);
		}

		problemWidgetMap.clear();
	}

	@UiHandler("createButton")
	void onClick(ClickEvent event) {
		getUiHandlers().createNewProblem();
	}
}
