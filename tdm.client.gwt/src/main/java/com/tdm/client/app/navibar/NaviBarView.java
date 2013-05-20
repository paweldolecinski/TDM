package com.tdm.client.app.navibar;

import com.github.gwtbootstrap.client.ui.DropdownButton;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class NaviBarView extends ViewImpl implements
		NaviBarPresenterWidget.Display {

	public interface Binder extends UiBinder<Widget, NaviBarView> {
	}

	private final Widget widget;
	@UiField
	protected NavLink learnLink;
	@UiField
	protected NavLink exploreLink;
	@UiField
	protected DropdownButton userMenu;

	@Inject
	public NaviBarView(Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiHandler({ "learnLink", "exploreLink" })
	void handleMenuClick(ClickEvent e) {
		Window.alert("Under construction. Sorry :(");
	}

	@Override
	public void setUserName(String username) {
		userMenu.setText(username);
	}
}
