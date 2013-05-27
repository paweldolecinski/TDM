package com.tdm.client.place;

import javax.inject.Inject;

import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.tdm.client.security.CurrentUser;

public class TdmPlaceManager extends PlaceManagerImpl {

	private final PlaceRequest defaultPlaceRequest;
	private final PlaceRequest errorPlaceRequest;
	private final PlaceRequest unauthorizedPlaceRequest;
	private final CurrentUser currentUser;
	private final Timer retryTimer;
	private final int retryDelay;
	private final int maxRetries;
	private int nbRetry;

	@Inject
	public TdmPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter,
			CurrentUser currentUser,
			@DefaultPlace String defaultPlaceNameToken,
			@ErrorPlace String errorPlaceNameToken,
			@UnauthorizedPlace String unauthorizedPlaceNameToken) {
		super(eventBus, tokenFormatter);
		this.currentUser = currentUser;
		this.retryDelay = 500;
		this.maxRetries = 4;

		this.retryTimer = new Timer() {
			@Override
			public void run() {
				revealCurrentPlace();
			}
		};

		defaultPlaceRequest = new PlaceRequest(defaultPlaceNameToken);
		errorPlaceRequest = new PlaceRequest(errorPlaceNameToken);
		unauthorizedPlaceRequest = new PlaceRequest(unauthorizedPlaceNameToken);
	}

	@Override
	public void revealDefaultPlace() {
		revealPlace(defaultPlaceRequest, false);
	}

	@Override
	public void revealErrorPlace(String invalidHistoryToken) {
		revealPlace(errorPlaceRequest, false);
	}

	@Override
	public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
		if (!currentUser.isConfirmed() && nbRetry < maxRetries) {
			nbRetry++;
			retryTimer.schedule(retryDelay);
		} else {
			revealPlace(unauthorizedPlaceRequest);
		}
	}

}
