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
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.tdm.client.dispatch.command.CreateGdmProblemAction;
import com.tdm.client.dispatch.command.CreateGdmProblemResult;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.event.NewGdmProblemEvent;
import com.tdm.client.place.NameTokens;
import com.tdm.domain.model.problem.dto.Problem;
import com.tdm.domain.model.problem.dto.ProblemJSO;

/**
 * The {@link PresenterWidget} of a dialog box that is meant to be displayed
 * only when its parent presenter is visible. Compare to
 * {@link GlobalDialogPresenterWidget}.
 */
public class NewProblemPresenterWidget extends
		PresenterWidget<NewProblemPresenterWidget.Display> implements
		NewProblemUiHandlers {

	private final DispatchAsync dispatch;
	private Validator validator;
	private final PlaceManager placeManager;

	/**
	 * {@link NewProblemPresenterWidget}'s PopupView.
	 */
	public interface Display extends PopupView,
			HasUiHandlers<NewProblemUiHandlers> {
		void showErrors(Set<ConstraintViolation<ProblemJSO>> violations);

		void onHide();
		
		void onReveal();
	}

	@Inject
	public NewProblemPresenterWidget(final EventBus eventBus,
			final Display view, final DispatchAsync dispatch,
			final PlaceManager placeManager) {
		super(eventBus, view);
		this.dispatch = dispatch;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		validator = Validation.buildDefaultValidatorFactory().getValidator();

	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getView().onReveal();
	}
	@Override
	protected void onHide() {
		super.onHide();
		getView().onHide();
	}

	@Override
	public void createProblem(String title, String description) {
		ProblemJSO toValidation = ProblemJSO.create(title, description);

		Set<ConstraintViolation<ProblemJSO>> violations = validator
				.validate(toValidation);

		if (!violations.isEmpty()) {
			getView().showErrors(violations);
		} else {
			dispatch.execute(new CreateGdmProblemAction(toValidation),
					new AsyncCallback<CreateGdmProblemResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("error executing command ", caught);
							ErrorOccuredEvent.fire(
									NewProblemPresenterWidget.this,
									caught.getMessage());
							getView().hide();
						}

						@Override
						public void onSuccess(CreateGdmProblemResult result) {
							Problem createdProblem = result.getCreatedProblem();
							NewGdmProblemEvent.fire(
									NewProblemPresenterWidget.this,
									createdProblem);
							PlaceRequest request = new PlaceRequest(
									NameTokens.problem).with(
									NameTokens.Params.problemId,
									createdProblem.getKey());
							placeManager.revealPlace(request);
							getView().hide();
						}
					});
		}
	}
}
