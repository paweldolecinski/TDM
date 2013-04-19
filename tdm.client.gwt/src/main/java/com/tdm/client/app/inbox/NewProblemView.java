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

package com.tdm.client.app.inbox;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.tdm.domain.model.problem.dto.ProblemJSO;

/**
 * The view implementation for
 * {@link com.gwtplatform.NewProblemPresenterWidget.tab.client.application.localdialog.LocalDialogPresenterWidget}
 * .
 */
public class NewProblemView extends
		PopupViewWithUiHandlers<NewProblemUiHandlers> implements
		NewProblemPresenterWidget.Display {

	public interface Binder extends UiBinder<PopupPanel, NewProblemView> {
	}

	@UiField
	Button okButton;
	@UiField
	TextBox titleBox;
	@UiField
	TextBox descBox;
	@UiField
	HTML errors;

	@Inject
	public NewProblemView(Binder uiBinder, EventBus eventBus) {
		super(eventBus);
		initWidget(uiBinder.createAndBindUi(this));
		asPopupPanel().setAutoHideEnabled(true);
		asPopupPanel().setAnimationEnabled(true);
		asPopupPanel().setGlassEnabled(true);
	}

	@UiHandler("okButton")
	void okButtonClicked(ClickEvent event) {
		getUiHandlers().createProblem(titleBox.getText(), descBox.getText());
	}

	public void showErrors(Set<ConstraintViolation<ProblemJSO>> violations) {
		StringBuilder builder = new StringBuilder();

		for (ConstraintViolation<?> violation : violations) {
			builder.append(violation.getMessage());
			builder.append(" : <i>(");
			builder.append(violation.getPropertyPath().toString());
			builder.append(" = ");
			builder.append("" + violation.getInvalidValue());
			builder.append(")</i>");
			builder.append("<br/>");
		}

		this.errors.setHTML(builder.toString());
	}

	public void clear() {
		titleBox.setText("");
		descBox.setText("");
		errors.setHTML("");
	}
}
