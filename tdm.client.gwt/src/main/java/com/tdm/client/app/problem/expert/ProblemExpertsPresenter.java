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
package com.tdm.client.app.problem.expert;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.dispatch.command.GetExpertListAction;
import com.tdm.client.dispatch.command.GetExpertListResult;
import com.tdm.client.dispatch.command.InviteExpertsAction;
import com.tdm.client.dispatch.command.InviteExpertsResult;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.event.navi.HideExpertsPresenterEvent;
import com.tdm.client.event.navi.HideExpertsPresenterEvent.HideExpertsPresenterHandler;
import com.tdm.client.event.navi.RevealExpertsPresenterEvent;
import com.tdm.client.event.navi.RevealExpertsPresenterEvent.RevealExpertsPresenterHandler;
import com.tdm.domain.model.expert.dto.ExpertInvitationJSO;
import com.tdm.domain.model.expert.dto.ExpertJso;

public class ProblemExpertsPresenter
		extends
		Presenter<ProblemExpertsPresenter.Display, ProblemExpertsPresenter.IProxy>
		implements RevealExpertsPresenterHandler, HideExpertsPresenterHandler,
		ProblemExpertsUiHandlers {

	private String problemId;
	private DispatchAsync dispatch;

	@ProxyCodeSplit
	public interface IProxy extends Proxy<ProblemExpertsPresenter> {
	}

	public interface Display extends View,
			HasUiHandlers<ProblemExpertsUiHandlers> {
		void clearList();

		void addExpert(ExpertJso expert);

		void mailsSent();
	}

	@Inject
	public ProblemExpertsPresenter(EventBus eventBus, Display view,
			IProxy proxy, final DispatchAsync dispatch) {
		super(eventBus, view, proxy, AppPresenter.TYPE_LeftContent);
		this.dispatch = dispatch;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onReset() {
		super.onReset();
		getExpertList();
	}

	private void getExpertList() {
		dispatch.execute(new GetExpertListAction(problemId),
				new AsyncCallback<GetExpertListResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Error executing command ", caught);
						ErrorOccuredEvent.fire(ProblemExpertsPresenter.this,
								caught);
					}

					@Override
					public void onSuccess(GetExpertListResult result) {
						getView().clearList();
						JsArray<ExpertJso> res = result.getExpertList();
						for (int i = 0; i < res.length(); i++) {
							getView().addExpert(res.get(i));
						}
					}
				});
	}

	@ProxyEvent
	@Override
	public void onRevealExpertsPresenter(RevealExpertsPresenterEvent event) {
		problemId = event.getProblemId();
		forceReveal();
	}

	@ProxyEvent
	@Override
	public void onHideExpertsPresenter(HideExpertsPresenterEvent event) {
		problemId = null;
		RevealContentEvent.fire(this, AppPresenter.TYPE_LeftContent, null);
	}

	@Override
	public void invite(List<String> mails, String msg) {
		JsArrayString createArray = JsArrayString.createArray().cast();
		for (String string : mails) {
			createArray.push(string);
		}
		ExpertInvitationJSO inv = ExpertInvitationJSO.create(createArray, msg);
		dispatch.execute(new InviteExpertsAction(problemId, inv),
				new AsyncCallback<InviteExpertsResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Error executing command ", caught);
						ErrorOccuredEvent.fire(ProblemExpertsPresenter.this,
								caught);
					}

					@Override
					public void onSuccess(InviteExpertsResult result) {
						getView().mailsSent();
					}
				});
	}
}
