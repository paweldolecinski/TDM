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
package com.tdm.client.app.problem;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.tdm.client.app.ui.WidgetList;
import com.tdm.client.resources.AppResources;

/**
 * @author Paweł Doleciński
 * 
 */
public class BrainstormView extends ViewWithUiHandlers<BrainstormUiHandlers>
		implements BrainstormPresenterWidget.Display {

	public interface Binder extends UiBinder<Widget, BrainstormView> {
	}

	private final Widget widget;

	@UiField
	WidgetList brainstormStream;

	private AppResources resources;

	@Inject
	public BrainstormView(Binder binder, AppResources resources) {
		this.resources = resources;
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.ViewImpl#addToSlot(java.lang.Object,
	 * com.google.gwt.user.client.ui.Widget)
	 */
	@Override
	public void addToSlot(Object slot, IsWidget content) {
		if (slot == BrainstormPresenterWidget.TYPE_Solution) {
			addSolution(content);
		} else {
			super.addToSlot(slot, content);
		}
	}

	/**
	 * @param content
	 */
	private void addSolution(IsWidget content) {
		if (content != null)
			brainstormStream.add(content);
	}

	@UiFactory
	WidgetList makeWidgetList() {
		return new WidgetList(resources);
	}
}
