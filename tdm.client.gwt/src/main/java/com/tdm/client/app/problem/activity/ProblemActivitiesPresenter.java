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

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.event.navi.HideActivitiesPresenterEvent;
import com.tdm.client.event.navi.HideActivitiesPresenterEvent.HideActivitiesPresenterHandler;
import com.tdm.client.event.navi.RevealActivitiesPresenterEvent;
import com.tdm.client.event.navi.RevealActivitiesPresenterEvent.RevealActivitiesPresenterHandler;

public class ProblemActivitiesPresenter extends
		Presenter<ProblemActivitiesPresenter.Display, ProblemActivitiesPresenter.IProxy>
		implements RevealActivitiesPresenterHandler, HideActivitiesPresenterHandler {

	private String problemId;

	@ProxyCodeSplit
	public interface IProxy extends Proxy<ProblemActivitiesPresenter> {
	}

	public interface Display extends View {
	}

	@Inject
	public ProblemActivitiesPresenter(EventBus eventBus, Display view, IProxy proxy) {
		super(eventBus, view, proxy, AppPresenter.TYPE_RightContent);
	}

	@Override
	protected void onBind() {
		super.onBind();
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
