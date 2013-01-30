/**
 * Copyright 2011 Paweł Doleciński.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pl.dolecinski.supdicium.client.presenter.root.view;

import pl.dolecinski.supdicium.client.SDStyle;
import pl.dolecinski.supdicium.client.presenter.root.RootWindowPresenter;
import pl.dolecinski.supdicium.client.theme.base.MainCss;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewImpl;

public class RootWindowView extends ViewImpl implements
		RootWindowPresenter.Display {

	public interface Binder extends UiBinder<Widget, RootWindowView> {
	}

	private final Widget widget;

	@UiField
	protected FlowPanel mainContentPanel;
	@UiField
	protected InlineHyperlink learnLink;
	@UiField
	protected InlineHyperlink exploreLink;

	@Inject
	public RootWindowView(Binder binder, EventBus eventBus) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == RootWindowPresenter.TYPE_MainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		mainContentPanel.clear();
		mainContentPanel.setVisible(true);
		if (content != null) {
			mainContentPanel.add(content);
		}
	}

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}

	@UiHandler({"learnLink", "exploreLink"})
	void handleMenuClick(ClickEvent e) {
		Window.alert("Under construction. Sorry :(");
	}

	public static native Element getBody() /*-{
		return $doc.body;
	}-*/;
}
