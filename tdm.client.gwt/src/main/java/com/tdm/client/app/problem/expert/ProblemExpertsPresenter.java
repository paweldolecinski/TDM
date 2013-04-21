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

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.event.navi.HideExpertsPresenterEvent;
import com.tdm.client.event.navi.HideExpertsPresenterEvent.HideExpertsPresenterHandler;
import com.tdm.client.event.navi.RevealExpertsPresenterEvent;
import com.tdm.client.event.navi.RevealExpertsPresenterEvent.RevealExpertsPresenterHandler;

public class ProblemExpertsPresenter extends
		Presenter<ProblemExpertsPresenter.Display, ProblemExpertsPresenter.IProxy>
		implements RevealExpertsPresenterHandler, HideExpertsPresenterHandler {

	@ProxyCodeSplit
	public interface IProxy extends Proxy<ProblemExpertsPresenter> {
	}

	public interface Display extends View {
	}

	@Inject
	public ProblemExpertsPresenter(EventBus eventBus, Display view, IProxy proxy) {
		super(eventBus, view, proxy, AppPresenter.TYPE_LeftContent);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@ProxyEvent
	@Override
	public void onRevealExpertsPresenter(RevealExpertsPresenterEvent event) {
		forceReveal();
	}

	@ProxyEvent
	@Override
	public void onHideExpertsPresenter(HideExpertsPresenterEvent event) {
		RevealContentEvent.fire(this, AppPresenter.TYPE_LeftContent, null);
	}
}
