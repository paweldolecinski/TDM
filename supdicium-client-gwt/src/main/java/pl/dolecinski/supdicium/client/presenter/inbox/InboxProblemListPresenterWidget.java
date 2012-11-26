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
package pl.dolecinski.supdicium.client.presenter.inbox;

import pl.dolecinski.supdicium.client.bl.NameTokens;
import pl.dolecinski.supdicium.client.dispatch.command.GetProblemListAction;
import pl.dolecinski.supdicium.client.dispatch.command.GetProblemListResult;
import pl.dolecinski.supdicium.client.event.ShowProblemListEvent;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemList.ProblemListUiHandlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class InboxProblemListPresenterWidget extends InboxProblemList implements
		ProblemListUiHandlers {

	private final PlaceManager placeManager;
	private DispatchAsync dispatch;

	@Inject
	public InboxProblemListPresenterWidget(EventBus eventBus,
			InboxProblemList.Display view, PlaceManager placeManager,
			final DispatchAsync dispatch) {
		super(eventBus, view);
		this.placeManager = placeManager;
		this.dispatch = dispatch;
		getView().setUiHandlers(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.HandlerContainerImpl#onBind()
	 */
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.PresenterWidget#onReset()
	 */
	@Override
	protected void onReset() {
		super.onReset();
		ShowProblemListEvent.fire(this, "");
	}

	private void getDecisionProblemList(String filter) {
		dispatch.execute(new GetProblemListAction(filter),
				new AsyncCallback<GetProblemListResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("error executing command ", caught);
					}

					@Override
					public void onSuccess(GetProblemListResult result) {
						getView().getProblemsDataProvider().setList(
								result.getProblemList().getProblems());
						getView().getProblemsDataProvider().refresh();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemList.
	 * ProblemListUiHandlers#showDecisionProblem(java.lang.String)
	 */
	@Override
	public void showDecisionProblem(String problemId) {
		placeManager.revealPlace(new PlaceRequest(NameTokens.problem).with(
				NameTokens.Params.id, problemId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemList.
	 * ProblemListUiHandlers#refreshProblemList(java.lang.String)
	 */
	@Override
	public void refreshProblemList(String filter) {
		ShowProblemListEvent.fire(this, filter);
	}

}
