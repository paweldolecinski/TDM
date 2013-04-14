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
package com.tdm.client.app;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.AsyncCallFailEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallFailHandler;
import com.gwtplatform.mvp.client.proxy.AsyncCallStartEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallStartHandler;
import com.gwtplatform.mvp.client.proxy.AsyncCallSucceedEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallSucceedHandler;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.tdm.client.app.popup.LocalDialogPresenterWidget;
import com.tdm.client.security.CurrentUser;
import com.tdm.client.security.CurrentUserChangedEvent;
import com.tdm.client.security.CurrentUserChangedEvent.CurrentUserChangedHandler;
import com.tdm.security.client.AuthService;
import com.tdm.security.client.AuthServiceAsync;

public class AppPresenter extends
	Presenter<AppPresenter.Display, AppPresenter.IProxy> implements
	CurrentUserChangedHandler, AsyncCallStartHandler, AsyncCallFailHandler,
	AsyncCallSucceedHandler {

    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_MainContent = new Type<RevealContentHandler<?>>();
    AuthServiceAsync authService = GWT.create(AuthService.class);
    private CurrentUser currentUser;

    public interface Display extends View {
    }

    @ProxyStandard
    public interface IProxy extends Proxy<AppPresenter> {
    }

    @Inject
    public AppPresenter(EventBus eventBus, Display view, IProxy proxy,
	    final LocalDialogPresenterWidget infoBox,
	    final CurrentUser currentUser) {
	super(eventBus, view, proxy);
	this.currentUser = currentUser;
    }

    @Override
    protected void onBind() {
	super.onBind();
    }

    @Override
    protected void revealInParent() {
	RevealRootContentEvent.fire(this, this);
    }

    @Override
    protected void onReveal() {
	super.onReveal();
    }

    @ProxyEvent
    @Override
    public void onCurrentUserChanged(CurrentUserChangedEvent event) {
    }

    @ProxyEvent
    @Override
    public void onAsyncCallStart(AsyncCallStartEvent event) {
	// addToPopupSlot(infoBox);
    }

    @ProxyEvent
    @Override
    public void onAsyncCallFail(AsyncCallFailEvent event) {
	// addToPopupSlot(null);
    }

    @ProxyEvent
    @Override
    public void onAsyncCallSucceed(AsyncCallSucceedEvent event) {
	// addToPopupSlot(null);
    }

    //
    // @ProxyEvent
    // public void onLockInteraction(LockInteractionEvent event) {
    // if (event.shouldLock())
    // getView().showLoading(true);
    // else
    // getView().showLoading(false);
    // }
    private void checkIfLoggedIn() {
	authService.checkAuthentication(new AsyncCallback<Boolean>() {

	    @Override
	    public void onFailure(Throwable caught) {
		currentUser.setAuthenticated(false);
	    }

	    @Override
	    public void onSuccess(Boolean result) {
		currentUser.setAuthenticated(result);
	    }
	});
    }
}
