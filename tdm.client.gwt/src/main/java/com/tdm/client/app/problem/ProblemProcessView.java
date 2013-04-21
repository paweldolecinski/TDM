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

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewImpl;
import com.tdm.client.resources.AppResources;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemProcessView extends ViewImpl implements
		ProblemProcessPresenter.Display {

	public interface Binder extends UiBinder<Widget, ProblemProcessView> {
	}

	private final Widget widget;

	@UiField
	protected UListElement solutionList;

	@UiField
	protected TextBox solutionText;

	@Inject
	public ProblemProcessView(Binder binder, EventBus eventBus,
			AppResources resources) {
		widget = binder.createAndBindUi(this);

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void focus() {
		solutionText.setFocus(true);
	}

}
