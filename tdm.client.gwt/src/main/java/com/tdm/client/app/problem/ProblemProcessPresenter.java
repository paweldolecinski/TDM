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
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.dispatch.command.CreateNewSolutionAction;
import com.tdm.client.dispatch.command.CreateNewSolutionResult;
import com.tdm.client.dispatch.command.GetProblemByIdAction;
import com.tdm.client.dispatch.command.GetProblemByIdResult;
import com.tdm.client.dispatch.command.GetSolutionIdeaListAction;
import com.tdm.client.dispatch.command.GetSolutionIdeaListResult;
import com.tdm.client.dispatch.command.VoteOnSolutionAction;
import com.tdm.client.dispatch.command.VoteOnSolutionResult;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.event.NewSolutionIdeaEvent;
import com.tdm.client.event.VoteOnSolutionEvent;
import com.tdm.client.event.navi.HideActivitiesPresenterEvent;
import com.tdm.client.event.navi.HideExpertsPresenterEvent;
import com.tdm.client.event.navi.RevealActivitiesPresenterEvent;
import com.tdm.client.event.navi.RevealExpertsPresenterEvent;
import com.tdm.client.place.NameTokens;
import com.tdm.domain.model.idea.dto.SolutionIdea;
import com.tdm.domain.model.idea.dto.SolutionIdeaJSO;
import com.tdm.domain.model.preferences.dto.SolutionPreferencesJSO;
import com.tdm.domain.model.preferences.dto.SolutionPreferencesJSO.NoteJSO;
import com.tdm.domain.model.problem.dto.ProblemJSO;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemProcessPresenter
		extends
		Presenter<ProblemProcessPresenter.Display, ProblemProcessPresenter.IProxy>
		implements ProblemProcessUiHandlers,
		NewSolutionIdeaEvent.NewSolutionIdeaHandler,
		VoteOnSolutionEvent.VoteOnSolutionHandler {

	public interface Display extends View,
			HasUiHandlers<ProblemProcessUiHandlers> {

		void setHeader(String title, String description);

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
	private ProblemJSO problem;
	private DispatchAsync dispatch;
	private PlaceManager placeManager;

	@Inject
	public ProblemProcessPresenter(EventBus eventBus, Display view,
			IProxy proxy, final DispatchAsync dispatch,
			PlaceManager placeManager) {
		super(eventBus, view, proxy, AppPresenter.TYPE_MainContent);
		this.dispatch = dispatch;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		RevealActivitiesPresenterEvent.fire(this, problemId);
		RevealExpertsPresenterEvent.fire(this, problemId);
		getView().setHeader(problem.getName(), problem.getDescription());
		getView().focus();
	}

	@Override
	protected void onHide() {
		super.onHide();
		problem = null;
		problemId = null;
		getView().clear();
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
		addRegisteredHandler(VoteOnSolutionEvent.getType(), this);
	}

	private void getSolutionIdeaList() {
		dispatch.execute(new GetSolutionIdeaListAction(problemId),
				new AsyncCallback<GetSolutionIdeaListResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Error executing command ", caught);
						ErrorOccuredEvent.fire(ProblemProcessPresenter.this,
								caught.getMessage());
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
								caught.getMessage());
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
	public void prepareFromRequest(final PlaceRequest request) {
		super.prepareFromRequest(request);
		problemId = request.getParameter(NameTokens.Params.problemId, null);
		String join = request.getParameter(NameTokens.Params.join, null);
		boolean shouldJoin = join != null;
		ManualRevealCallback<GetProblemByIdResult> revealCallback = ManualRevealCallback
				.create(this, new AsyncCallback<GetProblemByIdResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("error executing command ", caught);
						placeManager.revealErrorPlace(request.getNameToken());
					}

					@Override
					public void onSuccess(GetProblemByIdResult result) {
						problem = result.getProblem();
					}
				});

		dispatch.execute(new GetProblemByIdAction(problemId, shouldJoin),
				revealCallback);
	}

	@Override
	public boolean useManualReveal() {
		return true;
	}

	@Override
	public void onNewSolutionIdea(NewSolutionIdeaEvent event) {
		SolutionIdea created = event.getCreatedSolutionIdea();
		getView().addSolutionIdea(created);
	}

	@Override
	public void onVoteOnSolution(VoteOnSolutionEvent event) {
		NoteJSO note = NoteJSO.create(event.getSolution().getId(),
				event.getNote());
		JsArray<NoteJSO> notes = JsArray.createArray().cast();
		notes.push(note);
		SolutionPreferencesJSO preferences = SolutionPreferencesJSO.create(
				problemId, notes);

		dispatch.execute(new VoteOnSolutionAction(preferences),
				new AsyncCallback<VoteOnSolutionResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("error executing command ", caught);
						ErrorOccuredEvent.fire(ProblemProcessPresenter.this,
								caught.getMessage());
					}

					@Override
					public void onSuccess(VoteOnSolutionResult result) {
					}
				});
	}

}
