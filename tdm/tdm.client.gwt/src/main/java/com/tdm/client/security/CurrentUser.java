package com.tdm.client.security;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.tdm.client.dispatch.command.CheckAuthenticationAction;
import com.tdm.client.dispatch.command.CheckAuthenticationResult;

@Singleton
public class CurrentUser implements HasHandlers {
	private boolean authetnicated = false;
	private boolean firstCheck = true;

	private final EventBus eventBus;
	private DispatchAsync dispatcher;

	@Inject
	public CurrentUser(final EventBus eventBus, final DispatchAsync dispatcher) {
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
	}

	public void setAuthenticated(boolean authetnicated) {
		this.authetnicated = authetnicated;
		CurrentUserChangedEvent.fire(this);
	}

	public boolean isAuthenticated() {
		if(firstCheck){
			firstCheck = false;
			dispatcher.execute(new CheckAuthenticationAction(), new AsyncCallback<CheckAuthenticationResult>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(CheckAuthenticationResult result) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return authetnicated;
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		eventBus.fireEvent(event);
	}

}
