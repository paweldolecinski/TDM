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

package com.tdm.client.app.home;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.Form.SubmitEvent;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.tdm.domain.model.problem.dto.ProblemJSO;

/**
 * The view implementation for
 * {@link com.gwtplatform.SolutionIdeaPresenterWidget.tab.client.application.localdialog.LocalDialogPresenterWidget}
 * .
 */
public class NewProblemView extends
		PopupViewWithUiHandlers<NewProblemUiHandlers> implements
		NewProblemPresenterWidget.Display {

	public interface Binder extends UiBinder<PopupPanel, NewProblemView> {
	}

	@UiField
	WellForm form;
	@UiField
	TextBox titleBox;
	@UiField
	TextArea descBox;
	@UiField
	ControlGroup titleControlGroup;
	@UiField HelpInline titleError;
	
	@Inject
	public NewProblemView(Binder uiBinder, EventBus eventBus) {
		super(eventBus);
		initWidget(uiBinder.createAndBindUi(this));
		asPopupPanel().setAutoHideEnabled(true);
		asPopupPanel().setAnimationEnabled(true);
		asPopupPanel().setGlassEnabled(true);
	}

	@UiHandler("form")
	void onSubmit(SubmitEvent event) {
		event.cancel();
		getUiHandlers().createProblem(titleBox.getText(), descBox.getText());
	}

	@Override
	public void showErrors(Set<ConstraintViolation<ProblemJSO>> violations) {

		titleControlGroup.setType(ControlGroupType.ERROR);
		StringBuilder builder = new StringBuilder();

		for (ConstraintViolation<?> violation : violations) {
			builder.append(violation.getMessage());
			builder.append(" ");
		}
		titleError.setText(builder.toString());
	}

	@Override
	public void onHide() {
		titleBox.setText("");
		descBox.setText("");
		titleControlGroup.setType(ControlGroupType.NONE);
		titleError.setText(null);
	}

	@Override
	public void onReveal() {
		titleBox.setFocus(true);
	}
}
