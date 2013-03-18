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
package com.tdm.client.presenter.voting.view;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.tdm.client.SDStyle;
import com.tdm.client.presenter.voting.VotingPresenterWidget;
import com.tdm.client.theme.base.MainCss;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemVotingView extends ViewImpl implements
		VotingPresenterWidget.Display {

	public interface Binder extends UiBinder<Widget, ProblemVotingView> {
	}

	private final Widget widget;

	@Inject
	public ProblemVotingView(Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}
}
