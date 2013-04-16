package com.tdm.client.app.navibar;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class NaviBarPresenterWidget extends
		PresenterWidget<NaviBarPresenterWidget.Display> {

	public interface Display extends View {

	}

	@Inject
	public NaviBarPresenterWidget(EventBus eventBus, Display view) {
		super(eventBus, view);
	}

}
