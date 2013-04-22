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
import com.tdm.client.app.AppPresenter;
import com.tdm.client.dispatch.command.GetProblemListAction;
import com.tdm.client.dispatch.command.GetProblemListResult;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.place.NameTokens;
import com.tdm.domain.model.problem.dto.Problem;
import com.tdm.domain.model.problem.dto.ProblemJSO;

/**
 * @author Paweł Doleciński
 * 
 */
public class HomePresenter extends
		Presenter<HomePresenter.Display, HomePresenter.IProxy>
		implements HomeUiHandlers {

	public interface Display extends View, HasUiHandlers<HomeUiHandlers> {

		void addProblemListItem(Problem problem);

		void clearProblemList();

	}

	@NameToken(NameTokens.inbox)
	@ProxyCodeSplit
	public interface IProxy extends ProxyPlace<HomePresenter> {
	}

	private final DispatchAsync dispatch;
	private final NewProblemPresenterWidget newProblemDialog;

	@Inject
	public HomePresenter(EventBus eventBus, Display view, IProxy proxy,
			final DispatchAsync dispatch,
			NewProblemPresenterWidget newProblemDialog) {
		super(eventBus, view, proxy, AppPresenter.TYPE_MainContent);
		this.dispatch = dispatch;
		this.newProblemDialog = newProblemDialog;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onReset() {
		super.onReset();
		getDecisionProblemList(null);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	private void getDecisionProblemList(String filter) {
		getView().clearProblemList();

		dispatch.execute(new GetProblemListAction(filter),
				new AsyncCallback<GetProblemListResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Error executing command ", caught);
						ErrorOccuredEvent.fire(HomePresenter.this, caught);
					}

					@Override
					public void onSuccess(GetProblemListResult result) {
						JsArray<ProblemJSO> problemList = result
								.getProblemList();
						for (int i = problemList.length()-1; i >= 0; i--) {
							getView().addProblemListItem(problemList.get(i));
						}
					}
				});
	}

	@Override
	public void refreshProblemList(String filter) {
		getDecisionProblemList(filter);
	}

	@Override
	public void createNewProblem() {
		addToPopupSlot(newProblemDialog);
	}
}
