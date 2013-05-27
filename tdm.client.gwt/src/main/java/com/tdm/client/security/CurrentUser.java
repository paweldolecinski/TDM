package com.tdm.client.security;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.tdm.domain.model.user.dto.LocalUser;
import com.tdm.security.client.AuthService;
import com.tdm.security.client.AuthServiceAsync;

@Singleton
public class CurrentUser implements HasHandlers {

	private final EventBus eventBus;

	AuthServiceAsync authService = GWT.create(AuthService.class);

	private final int retryDelay;
	private final Timer fetchUserTimer;

	private LocalUser user;
	private boolean confirmed;

	@Inject
	public CurrentUser(final EventBus eventBus, final DispatchAsync dispatcher) {
		this.eventBus = eventBus;
		// TODO These should be injected when GIN supports toInstance injection
		this.retryDelay = 10000;
		this.fetchUserTimer = new Timer() {
			@Override
			public void run() {
				fetchUser();
			}
		};
	}

	public void fetchUser() {
		fetchUserTimer.cancel();
		authService.checkAuthentication(new AsyncCallback<LocalUser>() {

			@Override
			public void onFailure(Throwable caught) {
				confirmed = true; // Async call is back. We know if user
									// is logged-in or not.
				failed();
			}

			@Override
			public void onSuccess(LocalUser result) {
				confirmed = true; // Async call is back. We know if user
									// is logged-in or not.
				if (result != null) {
					user = result;
					CurrentUserChangedEvent.fire(CurrentUser.this);
				} else {
					failed();
				}
			}

			private void failed() {
				user = null; // Nobody is logged in
				scheduleFetchUser(retryDelay);
				CurrentUserChangedEvent.fire(CurrentUser.this);
			}
		});
	}

	private void scheduleFetchUser(int delay) {
		fetchUserTimer.cancel();
		fetchUserTimer.schedule(delay);
	}

	public boolean isAuthenticated() {
		return user != null;
	}

	/**
	 * The user is unconfirmed when the application starts, before the first
	 * asynchronous call to the server has returned. As soon as this call as
	 * returned, we know if the user is logged in or not and this method will
	 * return <code>true</code>. At that point, call {@link #isLoggedIn()}.
	 * 
	 * @return <code>true</code> if the user is confirmed. <code>false</code>
	 *         otherwise.
	 */
	public boolean isConfirmed() {
		return confirmed;
	}

	public LocalUser getUser() {
		return user;
	}

	public String getUsername() {
		if (user == null) {
			return null;
		}
		return user.getUsername();
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		eventBus.fireEvent(event);
	}

}
