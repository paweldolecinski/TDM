package com.tdm.client.app;

import javax.inject.Inject;

import com.google.gwt.user.client.Command;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.tdm.client.security.CurrentUser;

public class SecurityBootstrapper implements Bootstrapper {
	private final PlaceManager placeManager;
	final Command command;

	@Inject
	public SecurityBootstrapper(PlaceManager placeManager,
			final CurrentUser currentUser, final EventBus eventBus) {
		this.placeManager = placeManager;
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

}
