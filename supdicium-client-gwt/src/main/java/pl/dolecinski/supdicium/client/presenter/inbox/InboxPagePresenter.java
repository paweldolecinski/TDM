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
import pl.dolecinski.supdicium.client.event.ShowProblemListEvent;
import pl.dolecinski.supdicium.client.model.problem.ProblemInfo;
import pl.dolecinski.supdicium.client.presenter.inbox.view.ProblemListUiHandlers;
import pl.dolecinski.supdicium.client.presenter.root.RootWindowPresenter;

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

/**
 * @author Paweł Doleciński
 * 
 */
public class InboxPagePresenter extends
		Presenter<InboxPagePresenter.Display, InboxPagePresenter.IProxy>
		implements ProblemListUiHandlers {

	public interface Display extends View, HasUiHandlers<ProblemListUiHandlers> {
		void addProblemListItem(ProblemInfo problem);
		
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
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, RootWindowPresenter.TYPE_MainContent,
				this);
	}

	private void getDecisionProblemList(String filter) {
		getView().problemCounter(10);
		getView().clearProblemList();
		for (int i = 0; i < 10; i++) {
			getView().addProblemListItem(
					new ProblemInfo("problem" + i, "Title" + i, "Description",
							null));

		}

		// dispatch.execute(new GetProblemListAction(filter),
		// new AsyncCallback<GetProblemListResult>() {
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// GWT.log("error executing command ", caught);
		// }
		//
		// @Override
		// public void onSuccess(GetProblemListResult result) {
		// getView().getProblemsDataProvider().setList(
		// result.getProblemList().getProblems());
		// getView().getProblemsDataProvider().refresh();
		// }
		// });
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
