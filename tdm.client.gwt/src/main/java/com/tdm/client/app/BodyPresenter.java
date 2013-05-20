package com.tdm.client.app;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.tdm.client.app.navibar.NaviBarPresenterWidget;
import com.tdm.client.event.navi.RevealWelcomeContentEvent;
import com.tdm.client.event.navi.RevealWelcomeContentEvent.RevealWelcomeContentHandler;
import com.tdm.client.resources.AppResources;

public class BodyPresenter extends RootPresenter implements
		RevealWelcomeContentHandler {

	/**
	 * {@link BodyPresenter}'s view.
	 */
	public static class BodyView extends RootView {

		private AppResources resources;

		@Inject
		public BodyView(AppResources resources) {
			this.resources = resources;
		}

		@Override
		public void setInSlot(Object slot, IsWidget content) {

			if (slot.equals(menuSlot)) {
				RootPanel naviBarPanel = RootPanel.get("navibar");
				naviBarPanel.clear();
				if (content != null) {
					naviBarPanel.addStyleName(resources.naviBar()
							.navbarFixedTop());
					naviBarPanel.add(content);
				}
			}
			if (slot.equals(rootSlot) && content != null) {
				RootPanel.get("main_container").clear();
				RootPanel.get("main_container").add(content);
			}
		}

	}

	private static final Object rootSlot = new Object();
	private static final Object menuSlot = new Object();
	private NaviBarPresenterWidget naviBar;

	/**
	 * Creates a proxy class for a presenter that can contain tabs.
	 * 
	 * @param eventBus
	 *            The event bus.
	 */
	@Inject
	public BodyPresenter(final EventBus eventBus, final BodyView view,
			final NaviBarPresenterWidget naviBar) {
		super(eventBus, view);
		this.naviBar = naviBar;
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(RevealWelcomeContentEvent.getType(), this);
	}

	@Override
	public void onRevealRootContent(
			final RevealRootContentEvent revealContentEvent) {
		setInSlot(menuSlot, naviBar, false);
		setInSlot(rootSlot, revealContentEvent.getContent());
	}

	public void onRevealRootLayoutContent(
			final RevealRootLayoutContentEvent revealContentEvent) {
		// NOOP
	}

	@Override
	public void onRevealWelcomeContent(RevealWelcomeContentEvent event) {
		setInSlot(menuSlot, null, false);
		setInSlot(rootSlot, event.getContent());
	}

}
