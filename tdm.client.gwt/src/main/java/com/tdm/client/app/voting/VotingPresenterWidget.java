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
package com.tdm.client.app.voting;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.event.navi.HideVotingPresenterEvent;
import com.tdm.client.event.navi.HideVotingPresenterEvent.HideVotingPresenterHandler;
import com.tdm.client.event.navi.RevealVotingPresenterEvent;
import com.tdm.client.event.navi.RevealVotingPresenterEvent.RevealVotingPresenterHandler;

public class VotingPresenterWidget extends
		Presenter<VotingPresenterWidget.Display, VotingPresenterWidget.IProxy>
		implements RevealVotingPresenterHandler, HideVotingPresenterHandler {

	@ProxyCodeSplit
	public interface IProxy extends Proxy<VotingPresenterWidget> {
	}

	public interface Display extends View {
	}

	@Inject
	public VotingPresenterWidget(EventBus eventBus, Display view, IProxy proxy) {
		super(eventBus, view, proxy, AppPresenter.TYPE_RightContent);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@ProxyEvent
	@Override
	public void onRevealVotingPresenter(RevealVotingPresenterEvent event) {
		forceReveal();
	}

	@ProxyEvent
	@Override
	public void onHideVotingPresenter(HideVotingPresenterEvent event) {
		RevealContentEvent.fire(this, AppPresenter.TYPE_RightContent, null);
	}
}
