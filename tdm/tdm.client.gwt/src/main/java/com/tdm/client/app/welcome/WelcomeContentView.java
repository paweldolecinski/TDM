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
package com.tdm.client.app.welcome;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewImpl;

public class WelcomeContentView extends ViewImpl implements
		WelcomeContentPresenter.Display {

	public interface Binder extends UiBinder<Widget, WelcomeContentView> {
	}

	private final Widget widget;

	@UiField
	protected FlowPanel header;
	@UiField
	protected FlowPanel footer;

	@UiField
	protected FlowPanel mainPanel;

	@Inject
	public WelcomeContentView(Binder binder, EventBus eventBus) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == WelcomeContentPresenter.TYPE_MainContent) {
			setMainContent(content);
		} else if (slot == WelcomeContentPresenter.TYPE_Header) {
			setHeader(content);
		} else if (slot == WelcomeContentPresenter.TYPE_Footer) {
			setFooter(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		mainPanel.clear();
		if (content != null) {
			mainPanel.add(content);
		}
	}

	private void setHeader(Widget content) {
		header.clear();
		if (content != null) {
			header.add(content);
		}
	}

	private void setFooter(Widget content) {
		footer.clear();
		if (content != null) {
			footer.add(content);
		}
	}
}
