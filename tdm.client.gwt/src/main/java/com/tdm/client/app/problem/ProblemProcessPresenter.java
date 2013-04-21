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

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.event.navi.HideActivitiesPresenterEvent;
import com.tdm.client.event.navi.HideExpertsPresenterEvent;
import com.tdm.client.event.navi.RevealActivitiesPresenterEvent;
import com.tdm.client.event.navi.RevealExpertsPresenterEvent;
import com.tdm.client.place.NameTokens;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemProcessPresenter
		extends
		Presenter<ProblemProcessPresenter.Display, ProblemProcessPresenter.IProxy> {

	public interface Display extends View {
		void focus();
	}

	@NameToken(NameTokens.problem)
	@ProxyCodeSplit
	public interface IProxy extends ProxyPlace<ProblemProcessPresenter> {
	}

	@Inject
	public ProblemProcessPresenter(EventBus eventBus, Display view,
			IProxy proxy) {
		super(eventBus, view, proxy, AppPresenter.TYPE_MainContent);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		RevealActivitiesPresenterEvent.fire(this);
		RevealExpertsPresenterEvent.fire(this);
		getView().focus();
	}

	@Override
	protected void onHide() {
		super.onHide();
		HideActivitiesPresenterEvent.fire(this);
		HideExpertsPresenterEvent.fire(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

}
