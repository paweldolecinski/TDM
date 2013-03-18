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
package com.tdm.client.presenter.welcome.view;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.tdm.client.presenter.welcome.LoginPresenter;

public class LoginView extends ViewImpl implements LoginPresenter.Display {

	public interface Binder extends UiBinder<Widget, LoginView> {
	}

	@UiField
	protected FlowPanel googleBox;

	@UiField
	protected FlowPanel fcbBox;

	private final Widget widget;

	@Inject
	public LoginView(Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == LoginPresenter.TYPE_GoogleBox) {
			setGoogleBox(content);
		} else if (slot == LoginPresenter.TYPE_FcbBox) {
			setFcbBox(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setFcbBox(Widget content) {
		fcbBox.clear();
		if (content != null) {
			fcbBox.add(content);
		}
	}

	private void setGoogleBox(Widget content) {
		googleBox.clear();
		if (content != null) {
			googleBox.add(content);
		}
	}

}
