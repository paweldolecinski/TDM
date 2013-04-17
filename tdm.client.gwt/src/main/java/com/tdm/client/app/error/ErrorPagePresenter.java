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
package com.tdm.client.app.error;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.place.NameTokens;

/**
 * @author Paweł Doleciński
 * 
 */
public class ErrorPagePresenter extends
		Presenter<ErrorPagePresenter.Display, ErrorPagePresenter.IProxy> {

	public interface Display extends View {

		void setErrorText(String text);
	}

	@NameToken(NameTokens.error)
	@ProxyCodeSplit
	@NoGatekeeper
	public interface IProxy extends ProxyPlace<ErrorPagePresenter> {
	}

	@Inject
	public ErrorPagePresenter(EventBus eventBus, Display view, IProxy proxy) {
		super(eventBus, view, proxy, AppPresenter.TYPE_MainContent);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(ErrorOccuredEvent.getType(),
				new ErrorOccuredEvent.ErrorOccuredHandler() {

					@Override
					public void onErrorOccured(ErrorOccuredEvent event) {
						getView().setErrorText(
								event.getCaught().getLocalizedMessage());
					}
				});
	}

}
