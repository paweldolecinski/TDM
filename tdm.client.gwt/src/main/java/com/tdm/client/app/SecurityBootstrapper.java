package com.tdm.client.app;

import javax.inject.Inject;

import com.google.gwt.user.client.Command;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.tdm.client.security.CurrentUser;
import com.tdm.client.security.CurrentUserChangedEvent;
import com.tdm.client.security.CurrentUserChangedEvent.CurrentUserChangedHandler;

public class SecurityBootstrapper implements Bootstrapper,
		CurrentUserChangedHandler {
	private final PlaceManager placeManager;
	final Command command;

	@Inject
	public SecurityBootstrapper(PlaceManager placeManager,
			final CurrentUser currentUser, final EventBus eventBus) {
		this.placeManager = placeManager;
		eventBus.addHandler(CurrentUserChangedEvent.getType(), this);
		command = new Command() {
			@Override
			public void execute() {
				currentUser.fetchUser();
			}
		};
	}

	@Override
	public void onBootstrap() {
		command.execute();
		placeManager.revealCurrentPlace();
	}

	@Override
	public void onCurrentUserChanged(CurrentUserChangedEvent event) {

	}

	// private void checkIfLoggedIn() {
	//
	//
	//
	// // authService.checkAuthentication(new AsyncCallback<Boolean>() {
	// //
	// // @Override
	// // public void onFailure(Throwable caught) {
	// // currentUser.setAuthenticated(false);
	// // placeManager.revealCurrentPlace();
	// // }
	// //
	// // @Override
	// // public void onSuccess(Boolean result) {
	// // currentUser.setAuthenticated(result);
	// // placeManager.revealCurrentPlace();
	// // }
	// // });
	// }

}
