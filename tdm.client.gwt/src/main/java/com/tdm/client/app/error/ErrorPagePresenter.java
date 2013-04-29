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
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.event.ErrorOccuredEvent;
import com.tdm.client.place.NameTokens;

/**
 * @author Paweł Doleciński
 * 
 */
public class ErrorPagePresenter extends
		Presenter<ErrorPagePresenter.Display, ErrorPagePresenter.IProxy>
		implements ErrorOccuredEvent.ErrorOccuredHandler {

	private PlaceManager placeManager;

	public interface Display extends View {

		void setErrorText(String text);
	}

	@NameToken(NameTokens.error)
	@ProxyCodeSplit
	@NoGatekeeper
	public interface IProxy extends ProxyPlace<ErrorPagePresenter> {
	}

	@Inject
	public ErrorPagePresenter(EventBus eventBus, Display view, IProxy proxy,
			PlaceManager placeManager) {
		super(eventBus, view, proxy, AppPresenter.TYPE_MainContent);
		this.placeManager = placeManager;
	}

	@ProxyEvent
	@Override
	public void onErrorOccured(ErrorOccuredEvent event) {
		Throwable caught = event.getCaught();
		StackTraceElement[] stackTrace = caught.getStackTrace();
		String stack = "";
		for (StackTraceElement stackTraceElement : stackTrace) {
			stack += stackTraceElement.toString() + "\n";
		}
		getView().setErrorText(caught.getMessage() + " stack: " + stack);
		placeManager.revealErrorPlace(placeManager.getCurrentPlaceRequest()
				.getNameToken());
	}

}
