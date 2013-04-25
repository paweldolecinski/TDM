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
package com.tdm.client.app.problem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.dispatch.command.CreateNewSolutionAction;
import com.tdm.client.dispatch.command.CreateNewSolutionResult;
import com.tdm.client.dispatch.command.GetSolutionIdeaListAction;
import com.tdm.client.dispatch.command.GetSolutionIdeaListResult;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.event.NewSolutionIdeaEvent;
import com.tdm.client.event.navi.HideActivitiesPresenterEvent;
import com.tdm.client.event.navi.HideExpertsPresenterEvent;
import com.tdm.client.event.navi.RevealActivitiesPresenterEvent;
import com.tdm.client.event.navi.RevealExpertsPresenterEvent;
import com.tdm.client.place.NameTokens;
import com.tdm.domain.model.idea.dto.SolutionIdea;
import com.tdm.domain.model.idea.dto.SolutionIdeaJSO;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemProcessPresenter
		extends
		Presenter<ProblemProcessPresenter.Display, ProblemProcessPresenter.IProxy>
		implements ProblemProcessUiHandlers,
		NewSolutionIdeaEvent.NewSolutionIdeaHandler {

	public interface Display extends View,
			HasUiHandlers<ProblemProcessUiHandlers> {
		void focus();

		void addSolutionIdea(SolutionIdea solutionIdea);

		void clear();
	}

	@NameToken(NameTokens.problem)
	@ProxyCodeSplit
	public interface IProxy extends ProxyPlace<ProblemProcessPresenter> {
	}

	public static final Object solutionIdeaSlot = new Object();
	private String problemId;
	private DispatchAsync dispatch;

	@Inject
	public ProblemProcessPresenter(EventBus eventBus, Display view,
			IProxy proxy, final DispatchAsync dispatch) {
		super(eventBus, view, proxy, AppPresenter.TYPE_MainContent);
		this.dispatch = dispatch;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		RevealActivitiesPresenterEvent.fire(this);
		RevealExpertsPresenterEvent.fire(this);
		getView().focus();
	}

	@Override
	protected void onHide() {
		super.onHide();
		HideActivitiesPresenterEvent.fire(this);
		HideExpertsPresenterEvent.fire(this);
	}

	@Override
	protected void onReset() {
		super.onReset();
		getSolutionIdeaList();
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(NewSolutionIdeaEvent.getType(), this);
	}

	private void getSolutionIdeaList() {
		dispatch.execute(new GetSolutionIdeaListAction(problemId),
				new AsyncCallback<GetSolutionIdeaListResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Error executing command ", caught);
						ErrorOccuredEvent.fire(ProblemProcessPresenter.this,
								caught);
					}

					@Override
					public void onSuccess(GetSolutionIdeaListResult result) {
						getView().clear();
						JsArray<SolutionIdeaJSO> res = result
								.getSolutionIdeaList();
						for (int i = 0; i < res.length(); i++) {
							getView().addSolutionIdea(res.get(i));
						}
					}
				});
	}

	@Override
	public void createSolutionIdea(String text) {
		SolutionIdeaJSO solutionIdea = SolutionIdeaJSO.create(text);
		dispatch.execute(new CreateNewSolutionAction(problemId, solutionIdea),
				new AsyncCallback<CreateNewSolutionResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("error executing command ", caught);
						ErrorOccuredEvent.fire(ProblemProcessPresenter.this,
								caught);
					}

					@Override
					public void onSuccess(CreateNewSolutionResult result) {
						SolutionIdea created = result.getCreatedSolutionIdea();
						NewSolutionIdeaEvent.fire(ProblemProcessPresenter.this,
								created);
					}
				});
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		problemId = request.getParameter(NameTokens.Params.problemId, null);
	}

	@Override
	public void onNewSolutionIdea(NewSolutionIdeaEvent event) {
		SolutionIdea created = event.getCreatedSolutionIdea();
		getView().addSolutionIdea(created);
	}

	@Override
	public void refreshSolutionIdeaList() {
		// TODO Auto-generated method stub

	}
}
