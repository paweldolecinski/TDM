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
package com.tdm.client.gin.ui;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.tdm.client.app.AppPresenter;
import com.tdm.client.app.AppView;
import com.tdm.client.app.error.ErrorPagePresenter;
import com.tdm.client.app.error.view.ErrorPageView;
import com.tdm.client.app.inbox.InboxContentView;
import com.tdm.client.app.inbox.InboxPagePresenter;
import com.tdm.client.app.popup.LocalDialogPresenterWidget;
import com.tdm.client.app.popup.LocalDialogView;
import com.tdm.client.app.problem.BrainstormPresenterWidget;
import com.tdm.client.app.problem.BrainstormView;
import com.tdm.client.app.problem.NewSolutionPresenterWidget;
import com.tdm.client.app.problem.NewSolutionView;
import com.tdm.client.app.problem.ProblemDicussionPresenter;
import com.tdm.client.app.problem.ProblemDiscussionView;
import com.tdm.client.app.problem.SingleSolutionPresenterWidget;
import com.tdm.client.app.problem.SingleSolutionView;
import com.tdm.client.app.voting.ProblemVotingView;
import com.tdm.client.app.voting.VotingPresenterWidget;
import com.tdm.client.app.welcome.FacebookLoginPresenterWidget;
import com.tdm.client.app.welcome.FacebookLoginView;
import com.tdm.client.app.welcome.GoogleLoginPresenterWidget;
import com.tdm.client.app.welcome.GoogleLoginView;
import com.tdm.client.app.welcome.HomePresenter;
import com.tdm.client.app.welcome.HomeView;
import com.tdm.client.app.welcome.LoginPresenter;
import com.tdm.client.app.welcome.LoginView;
import com.tdm.client.app.welcome.WelcomeContentPresenter;
import com.tdm.client.app.welcome.WelcomeContentView;

public class ApplicationModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		install(new UiModule());
		
		bindPresenter(AppPresenter.class,
				AppPresenter.Display.class, AppView.class,
				AppPresenter.IProxy.class);

		bindPresenter(ErrorPagePresenter.class,
				ErrorPagePresenter.Display.class, ErrorPageView.class,
				ErrorPagePresenter.IProxy.class);

		/*
		 * Welcome section
		 */
		bindPresenter(WelcomeContentPresenter.class,
				WelcomeContentPresenter.Display.class,
				WelcomeContentView.class, WelcomeContentPresenter.IProxy.class);

		bindPresenter(LoginPresenter.class, LoginPresenter.Display.class,
				LoginView.class, LoginPresenter.IProxy.class);

		bindSingletonPresenterWidget(GoogleLoginPresenterWidget.class,
				GoogleLoginPresenterWidget.Display.class, GoogleLoginView.class);

		bindSingletonPresenterWidget(FacebookLoginPresenterWidget.class,
				FacebookLoginPresenterWidget.Display.class,
				FacebookLoginView.class);

		bindPresenter(HomePresenter.class, HomePresenter.Display.class,
				HomeView.class, HomePresenter.IProxy.class);

		/*
		 * Inbox section
		 */
		bindPresenter(InboxPagePresenter.class,
				InboxPagePresenter.Display.class, InboxContentView.class,
				InboxPagePresenter.IProxy.class);

		/*
		 * Problem section
		 */
		bindPresenter(ProblemDicussionPresenter.class,
				ProblemDicussionPresenter.Display.class,
				ProblemDiscussionView.class,
				ProblemDicussionPresenter.IProxy.class);

		bindSingletonPresenterWidget(NewSolutionPresenterWidget.class,
				NewSolutionPresenterWidget.Display.class, NewSolutionView.class);

		bindSingletonPresenterWidget(BrainstormPresenterWidget.class,
				BrainstormPresenterWidget.Display.class, BrainstormView.class);

		bindSingletonPresenterWidget(VotingPresenterWidget.class,
				VotingPresenterWidget.Display.class, ProblemVotingView.class);

		bindPresenterWidget(SingleSolutionPresenterWidget.class,
				SingleSolutionPresenterWidget.Display.class,
				SingleSolutionView.class);

		/*
		 * Pop-ups section
		 */
		bindPresenterWidget(LocalDialogPresenterWidget.class,
				LocalDialogPresenterWidget.View.class, LocalDialogView.class);

	}
}
