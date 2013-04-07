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
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.dispatch.command.CreateGdmProblemAction;
import com.tdm.client.dispatch.command.CreateGdmProblemResult;
import com.tdm.client.dispatch.command.GetProblemListAction;
import com.tdm.client.dispatch.command.GetProblemListResult;
import com.tdm.client.event.NewGdmProblemEvent;
import com.tdm.client.event.ShowProblemListEvent;
import com.tdm.client.place.NameTokens;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.jso.GdmProblemJso;

/**
 * @author Paweł Doleciński
 * 
 */
public class InboxPagePresenter extends
		Presenter<InboxPagePresenter.Display, InboxPagePresenter.IProxy>
		implements ProblemListUiHandlers {

	public interface Display extends View, HasUiHandlers<ProblemListUiHandlers> {

		void addProblemListItem(GdmProblem problem);

		void clearProblemList();

		void problemCounter(int amount);
	}

	@NameToken(NameTokens.inbox)
	@ProxyCodeSplit
	public interface IProxy extends ProxyPlace<InboxPagePresenter> {
	}

	private DispatchAsync dispatch;

	@Inject
	public InboxPagePresenter(EventBus eventBus, Display view, IProxy proxy,
			final DispatchAsync dispatch) {
		super(eventBus, view, proxy);
		this.dispatch = dispatch;
		getView().setUiHandlers(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.PresenterWidget#onReset()
	 */
	@Override
	protected void onReset() {
		super.onReset();
		ShowProblemListEvent.fire(this, (String) null);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(ShowProblemListEvent.getType(),
				new ShowProblemListEvent.ShowProblemListHandler() {

					@Override
					public void onShowProblemList(ShowProblemListEvent event) {
						getDecisionProblemList(event.getFilter());
					}
				});
		addRegisteredHandler(NewGdmProblemEvent.getType(),
				new NewGdmProblemEvent.NewGdmProblemHandler() {

					@Override
					public void onNewGdmProblem(NewGdmProblemEvent event) {
						sendNewProblem();
					}
				});
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.TYPE_MainContent, this);
	}

	private void sendNewProblem() {
		GdmProblemJso problem = GdmProblemJso.createObject().cast();
		problem.setName("Problemo!");
		problem.setDescription("Molto grande problemo :)");
		dispatch.execute(new CreateGdmProblemAction(problem),
				new AsyncCallback<CreateGdmProblemResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("error executing command ", caught);
					}

					@Override
					public void onSuccess(CreateGdmProblemResult result) {
						GdmProblem createdProblem = result.getCreatedProblem();
						getView().addProblemListItem(createdProblem);
					}
				});
	}

	private void getDecisionProblemList(String filter) {
		getView().problemCounter(10);
		getView().clearProblemList();

		dispatch.execute(new GetProblemListAction(filter),
				new AsyncCallback<GetProblemListResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("error executing command ", caught);
					}

					@Override
					public void onSuccess(GetProblemListResult result) {
						JsArray<GdmProblemJso> problemList = result
								.getProblemList();
						for (int i = 0; i < problemList.length(); i++) {
							getView().addProblemListItem(problemList.get(i));
						}
					}
				});
	}

	@Override
	public void refreshProblemList(String filter) {
		ShowProblemListEvent.fire(this, filter);
	}

	@Override
	public void createNewProblem() {
		NewGdmProblemEvent.fire(this);
	}
}
