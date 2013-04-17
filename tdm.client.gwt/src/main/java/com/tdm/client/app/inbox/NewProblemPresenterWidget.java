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
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.tdm.client.dispatch.command.CreateGdmProblemAction;
import com.tdm.client.dispatch.command.CreateGdmProblemResult;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.event.NewGdmProblemEvent;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.jso.GdmProblemJso;

/**
 * The {@link PresenterWidget} of a dialog box that is meant to be displayed
 * only when its parent presenter is visible. Compare to
 * {@link GlobalDialogPresenterWidget}.
 */
public class NewProblemPresenterWidget extends
		PresenterWidget<NewProblemPresenterWidget.Display> implements
		NewProblemUiHandlers {

	private final DispatchAsync dispatch;
	Validator validator;

	/**
	 * {@link NewProblemPresenterWidget}'s PopupView.
	 */
	public interface Display extends PopupView,
			HasUiHandlers<NewProblemUiHandlers> {
		void showErrors(Set<ConstraintViolation<GdmProblemJso>> violations);

		void clear();
	}

	@Inject
	public NewProblemPresenterWidget(final EventBus eventBus,
			final Display view, final DispatchAsync dispatch) {
		super(eventBus, view);
		this.dispatch = dispatch;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		validator = Validation.buildDefaultValidatorFactory().getValidator();

	}

	@Override
	protected void onHide() {
		super.onHide();
		getView().clear();
	}

	@Override
	public void createProblem(String title, String description) {
		GdmProblemJso toValidation = GdmProblemJso.createObject().cast();
		toValidation.setName(title);
		toValidation.setDescription(description);

		Set<ConstraintViolation<GdmProblemJso>> violations = validator
				.validate(toValidation);

		if (!violations.isEmpty()) {
			getView().showErrors(violations);
		} else {
			GdmProblemJso problem = GdmProblemJso.createObject().cast();
			problem.setName(title);
			problem.setDescription(description);

			dispatch.execute(new CreateGdmProblemAction(problem),
					new AsyncCallback<CreateGdmProblemResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("error executing command ", caught);
							ErrorOccuredEvent.fire(
									NewProblemPresenterWidget.this, caught);
							getView().hide();
						}

						@Override
						public void onSuccess(CreateGdmProblemResult result) {
							GdmProblem createdProblem = result
									.getCreatedProblem();
							NewGdmProblemEvent.fire(
									NewProblemPresenterWidget.this,
									createdProblem);
							getView().hide();
						}
					});
		}
	}
}
