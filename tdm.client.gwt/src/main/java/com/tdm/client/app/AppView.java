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
package com.tdm.client.app;

import com.github.gwtbootstrap.client.ui.Column;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewImpl;

public class AppView extends ViewImpl implements AppPresenter.Display {

	public interface Binder extends UiBinder<Widget, AppView> {
	}

	private final Widget widget;

	@UiField
	protected Column mainContentPanel;
	@UiField
	protected Column rightContentPanel;
	@UiField
	protected Column leftContentPanel;
	
	@Inject
	public AppView(Binder binder, EventBus eventBus) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == AppPresenter.TYPE_MainContent) {
			setMainContent(content);
		} else if (slot == AppPresenter.TYPE_RightContent) {
			setRightContent(content);
		} else if (slot == AppPresenter.TYPE_LeftContent) {
			setLeftContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
	private void setLeftContent(IsWidget content) {
		leftContentPanel.clear();
		if (content != null) {
			leftContentPanel.add(content);
		}
	}
	
	private void setMainContent(IsWidget content) {
		mainContentPanel.clear();
		if (content != null) {
			mainContentPanel.add(content);
		}
	}

	private void setRightContent(IsWidget content) {
		rightContentPanel.clear();
		if (content != null) {
			rightContentPanel.add(content);
		}
	}
}
