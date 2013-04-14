package com.tdm.client.app;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.annotations.Bootstrap;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.tdm.client.security.CurrentUser;
import com.tdm.security.client.AuthService;
import com.tdm.security.client.AuthServiceAsync;

@Bootstrap
public class SecurityBootstrapper implements Bootstrapper {
    private final PlaceManager placeManager;
    private CurrentUser currentUser;
    AuthServiceAsync authService = GWT.create(AuthService.class);

    @Inject
    public SecurityBootstrapper(PlaceManager placeManager,
	    CurrentUser currentUser) {
	this.placeManager = placeManager;
	this.currentUser = currentUser;
    }

    @Override
    public void onBootstrap() {
	checkIfLoggedIn();
    }

    private void checkIfLoggedIn() {
	authService.checkAuthentication(new AsyncCallback<Boolean>() {

	    @Override
	    public void onFailure(Throwable caught) {
		currentUser.setAuthenticated(false);
		placeManager.revealCurrentPlace();
	    }

	    @Override
	    public void onSuccess(Boolean result) {
		currentUser.setAuthenticated(result);
		placeManager.revealCurrentPlace();
	    }
	});
    }
}
