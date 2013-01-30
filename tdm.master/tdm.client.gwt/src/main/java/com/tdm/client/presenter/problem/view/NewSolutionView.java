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
package com.tdm.client.presenter.problem.view;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.tdm.client.SDStyle;
import com.tdm.client.presenter.problem.NewSolutionPresenterWidget;
import com.tdm.client.presenter.problem.NewSolutionUiHandlers;
import com.tdm.client.theme.base.MainCss;

/**
 * @author Paweł Doleciński
 * 
 */
public class NewSolutionView extends ViewWithUiHandlers<NewSolutionUiHandlers> implements
		NewSolutionPresenterWidget.Display, ClickHandler {

	public interface Binder extends UiBinder<Widget, NewSolutionView> {
	}

	@UiField
	TextBox solutionText;
	@UiField
	TextArea solutionAdditionalText;
	@UiField
	Button addNewSolution;
	
	private final Widget widget;

	@Inject
	public NewSolutionView(Binder binder) {
		widget = binder.createAndBindUi(this);
		
		addNewSolution.addClickHandler(this);
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
	public void onClick(ClickEvent event) {
		getUiHandlers().addNewSolution(solutionText.getText(), solutionAdditionalText.getText());
	}

	@Override
	public void resetFields() {
		solutionText.setText("");
		solutionAdditionalText.setText("");
	}
}
