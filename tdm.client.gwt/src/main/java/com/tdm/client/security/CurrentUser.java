package com.tdm.client.security;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

@Singleton
public class CurrentUser implements HasHandlers {
    private boolean authetnicated;

    private final EventBus eventBus;

    @Inject
    public CurrentUser(final EventBus eventBus) {
	this.eventBus = eventBus;
    }

    public void setAuthenticated(boolean authetnicated) {
	this.authetnicated = authetnicated;
	CurrentUserChangedEvent.fire(this);
    }

    public boolean isAuthenticated() {
	return authetnicated;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
	eventBus.fireEvent(event);
    }

}
