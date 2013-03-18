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
package com.tdm.client.gin.module.ui;


import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.tdm.client.presenter.error.ErrorPagePresenter;
import com.tdm.client.presenter.error.view.ErrorPageView;
import com.tdm.client.presenter.inbox.InboxPagePresenter;
import com.tdm.client.presenter.inbox.view.InboxContentView;
import com.tdm.client.presenter.popup.LocalDialogPresenterWidget;
import com.tdm.client.presenter.popup.view.LocalDialogView;
import com.tdm.client.presenter.problem.BrainstormPresenterWidget;
import com.tdm.client.presenter.problem.NewSolutionPresenterWidget;
import com.tdm.client.presenter.problem.ProblemDicussionPresenter;
import com.tdm.client.presenter.problem.SingleSolutionPresenterWidget;
import com.tdm.client.presenter.problem.view.BrainstormView;
import com.tdm.client.presenter.problem.view.NewSolutionView;
import com.tdm.client.presenter.problem.view.ProblemDiscussionView;
import com.tdm.client.presenter.problem.view.SingleSolutionView;
import com.tdm.client.presenter.root.RootWindowPresenter;
import com.tdm.client.presenter.root.view.RootWindowView;
import com.tdm.client.presenter.voting.VotingPresenterWidget;
import com.tdm.client.presenter.voting.view.ProblemVotingView;
import com.tdm.client.presenter.welcome.FacebookLoginPresenterWidget;
import com.tdm.client.presenter.welcome.GoogleLoginPresenterWidget;
import com.tdm.client.presenter.welcome.HomePresenter;
import com.tdm.client.presenter.welcome.LoginPresenter;
import com.tdm.client.presenter.welcome.WelcomeContentPresenter;
import com.tdm.client.presenter.welcome.view.FacebookLoginView;
import com.tdm.client.presenter.welcome.view.GoogleLoginView;
import com.tdm.client.presenter.welcome.view.HomeView;
import com.tdm.client.presenter.welcome.view.LoginView;
import com.tdm.client.presenter.welcome.view.WelcomeContentView;
import com.tdm.client.ui.FooterWidget;
import com.tdm.client.ui.ProblemListItemWidget;
import com.tdm.client.ui.solution.AddCommentBox;
import com.tdm.client.ui.solution.CommentWidget;

public abstract class BasePresenterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		bindLayoutDependPresenters();

		bindPresenter(RootWindowPresenter.class,
				RootWindowPresenter.Display.class, RootWindowView.class,
				RootWindowPresenter.IProxy.class);

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

		bind(ProblemListItemWidget.class).toProvider(
				ProblemListItemWidget.Provider.class);
		/*
		 * Widgets
		 */
		bind(FooterWidget.Binder.class).in(Singleton.class);
		bind(CommentWidget.Binder.class).in(Singleton.class);
		bind(AddCommentBox.Binder.class).in(Singleton.class);
	}

	protected abstract void bindLayoutDependPresenters();
}
