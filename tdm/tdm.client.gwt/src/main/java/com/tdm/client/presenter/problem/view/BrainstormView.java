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
package com.tdm.client.presenter.problem.view;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.tdm.client.presenter.problem.BrainstormPresenterWidget;
import com.tdm.client.presenter.problem.BrainstormUiHandlers;
import com.tdm.client.ui.WidgetList;

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
	protected WidgetList brainstormStream;

	@Inject
	public BrainstormView(Binder binder) {
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
	public void addToSlot(Object slot, Widget content) {
		if (slot == BrainstormPresenterWidget.TYPE_Solution) {
			addSolution(content);
		} else {
			super.addToSlot(slot, content);
		}
	}

	/**
	 * @param content
	 */
	private void addSolution(Widget content) {
		if (content != null)
			brainstormStream.add(content);
	}

}
