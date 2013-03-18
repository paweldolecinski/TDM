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
package com.tdm.client.presenter.welcome;


import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.tdm.client.bl.NameTokens;

public class LoginPresenter extends
		Presenter<LoginPresenter.Display, LoginPresenter.IProxy> {

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_GoogleBox = new Type<RevealContentHandler<?>>();
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_FcbBox = new Type<RevealContentHandler<?>>();

	private final GoogleLoginPresenterWidget googleLogin;
	private final FacebookLoginPresenterWidget fcbLogin;

	public interface Display extends View {
	}

	@NameToken(NameTokens.login)
	@ProxyCodeSplit
	public interface IProxy extends ProxyPlace<LoginPresenter> {
	}

	@Inject
	public LoginPresenter(EventBus eventBus, Display view, IProxy proxy,
			GoogleLoginPresenterWidget googleLogin,
			FacebookLoginPresenterWidget fcbLogin) {
		super(eventBus, view, proxy);
		this.googleLogin = googleLogin;
		this.fcbLogin = fcbLogin;
	}

	@Override
	protected void onBind() {
		super.onBind();
	}


	@Override
	protected void onReveal() {
		super.onReveal();
		setInSlot(TYPE_GoogleBox, googleLogin);
		setInSlot(TYPE_FcbBox, fcbLogin);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, WelcomeContentPresenter.TYPE_MainContent,
				this);
	}

}
