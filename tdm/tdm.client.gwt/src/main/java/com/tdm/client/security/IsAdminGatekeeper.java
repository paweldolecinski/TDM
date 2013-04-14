package com.tdm.client.security;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
//@DefaultGatekeeper
public class IsAdminGatekeeper implements Gatekeeper {
	private final CurrentUser currentUser;

	@Inject
	public IsAdminGatekeeper(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public boolean canReveal() {
		return currentUser.isAuthenticated();
	}

}
