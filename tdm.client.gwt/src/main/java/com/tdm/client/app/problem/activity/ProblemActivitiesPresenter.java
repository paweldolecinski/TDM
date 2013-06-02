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
package com.tdm.client.app.problem.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.dispatch.command.GetCurrentConsensusAction;
import com.tdm.client.dispatch.command.GetCurrentConsensusResult;
import com.tdm.client.event.ChannelMessageReceivedEvent;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.event.navi.HideActivitiesPresenterEvent;
import com.tdm.client.event.navi.HideActivitiesPresenterEvent.HideActivitiesPresenterHandler;
import com.tdm.client.event.navi.RevealActivitiesPresenterEvent;
import com.tdm.client.event.navi.RevealActivitiesPresenterEvent.RevealActivitiesPresenterHandler;
import com.tdm.domain.model.idea.dto.SolutionIdea;
import com.tdm.domain.model.problem.dto.CurrentConsensusJSO;

public class ProblemActivitiesPresenter
		extends
		Presenter<ProblemActivitiesPresenter.Display, ProblemActivitiesPresenter.IProxy>
		implements RevealActivitiesPresenterHandler,
		HideActivitiesPresenterHandler {

	private String problemId;
	private DispatchAsync dispatch;

	@ProxyCodeSplit
	public interface IProxy extends Proxy<ProblemActivitiesPresenter> {
	}

	public interface Display extends View {

		void clearRanking();

		void addToRanking(SolutionIdea solutionIdea);
	}

	@Inject
	public ProblemActivitiesPresenter(EventBus eventBus, Display view,
			IProxy proxy, final DispatchAsync dispatch) {
		super(eventBus, view, proxy, AppPresenter.TYPE_RightContent);
		this.dispatch = dispatch;
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(
				ChannelMessageReceivedEvent.getType(),
				new ChannelMessageReceivedEvent.ChannelMessageReceivedHandler() {

					@Override
					public void onChannelMessageReceived(
							ChannelMessageReceivedEvent event) {
						if (event.getMsg().equals("NEW_RESULT")) {
							getResult();
						}
					}
				});
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getResult();
	}

	private void getResult() {
		dispatch.execute(new GetCurrentConsensusAction(problemId),
				new AsyncCallback<GetCurrentConsensusResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Error executing command ", caught);
						ErrorOccuredEvent.fire(ProblemActivitiesPresenter.this,
								caught.getMessage());
					}

					@Override
					public void onSuccess(GetCurrentConsensusResult result) {
						getView().clearRanking();
						CurrentConsensusJSO consensus = result.getConsensus();
						List<SolutionIdea> res = new ArrayList<SolutionIdea>(
								consensus.getRanking());
						for (int i = 0; i < res.size(); i++) {
							SolutionIdea solutionIdea = res.get(i);
							getView().addToRanking(solutionIdea);
						}
					}
				});
	}

	@ProxyEvent
	@Override
	public void onRevealActivitiesPresenter(RevealActivitiesPresenterEvent event) {
		problemId = event.getProblemId();
		forceReveal();
	}

	@ProxyEvent
	@Override
	public void onHideActivitiesPresenter(HideActivitiesPresenterEvent event) {
		problemId = null;
		RevealContentEvent.fire(this, AppPresenter.TYPE_RightContent, null);
	}
}
