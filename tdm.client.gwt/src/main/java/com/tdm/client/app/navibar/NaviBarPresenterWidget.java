package com.tdm.client.app.navibar;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.tdm.client.security.CurrentUser;

public class NaviBarPresenterWidget extends
		PresenterWidget<NaviBarPresenterWidget.Display> {

	private CurrentUser currentUser;

	public interface Display extends View {
		void setUserName(String username);
	}

	@Inject
	public NaviBarPresenterWidget(EventBus eventBus, Display view,
			final DispatchAsync dispatch, final CurrentUser currentUser) {
		super(eventBus, view);
		this.currentUser = currentUser;
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getView().setUserName(currentUser.getUser().getName());
	}
}
