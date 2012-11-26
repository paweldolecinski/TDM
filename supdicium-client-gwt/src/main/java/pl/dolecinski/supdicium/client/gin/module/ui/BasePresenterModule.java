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
package pl.dolecinski.supdicium.client.gin.module.ui;

import pl.dolecinski.supdicium.client.presenter.error.ErrorPagePresenter;
import pl.dolecinski.supdicium.client.presenter.error.view.ErrorPageView;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxMenuPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxPagePresenter;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemList;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemListPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.inbox.view.InboxContentView;
import pl.dolecinski.supdicium.client.presenter.inbox.view.InboxMenuView;
import pl.dolecinski.supdicium.client.presenter.inbox.view.InboxProblemListView;
import pl.dolecinski.supdicium.client.presenter.popup.LocalDialogPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.popup.view.LocalDialogView;
import pl.dolecinski.supdicium.client.presenter.problem.BrainstormPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.problem.NewSolutionPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.problem.ProblemDicussionPresenter;
import pl.dolecinski.supdicium.client.presenter.problem.SingleSolutionPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.problem.view.BrainstormView;
import pl.dolecinski.supdicium.client.presenter.problem.view.NewSolutionView;
import pl.dolecinski.supdicium.client.presenter.problem.view.ProblemDiscussionView;
import pl.dolecinski.supdicium.client.presenter.problem.view.SingleSolutionView;
import pl.dolecinski.supdicium.client.presenter.root.RootWindowPresenter;
import pl.dolecinski.supdicium.client.presenter.root.view.RootWindowView;
import pl.dolecinski.supdicium.client.presenter.voting.VotingPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.voting.view.ProblemVotingView;
import pl.dolecinski.supdicium.client.presenter.welcome.FacebookLoginPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.welcome.GoogleLoginPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.welcome.HomePresenter;
import pl.dolecinski.supdicium.client.presenter.welcome.LoginPresenter;
import pl.dolecinski.supdicium.client.presenter.welcome.WelcomeContentPresenter;
import pl.dolecinski.supdicium.client.presenter.welcome.view.FacebookLoginView;
import pl.dolecinski.supdicium.client.presenter.welcome.view.GoogleLoginView;
import pl.dolecinski.supdicium.client.presenter.welcome.view.HomeView;
import pl.dolecinski.supdicium.client.presenter.welcome.view.LoginView;
import pl.dolecinski.supdicium.client.presenter.welcome.view.WelcomeContentView;
import pl.dolecinski.supdicium.client.ui.FooterWidget;
import pl.dolecinski.supdicium.client.ui.solution.AddCommentBox;
import pl.dolecinski.supdicium.client.ui.solution.CommentWidget;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

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

		bindSingletonPresenterWidget(InboxMenuPresenterWidget.class,
				InboxMenuPresenterWidget.Display.class, InboxMenuView.class);

		bindSingletonPresenterWidget(InboxProblemList.class,
				InboxProblemListPresenterWidget.class,
				InboxProblemList.Display.class, InboxProblemListView.class);

		/*
		 * Problem section
		 */
		bindPresenter(ProblemDicussionPresenter.class,
				ProblemDicussionPresenter.Display.class, ProblemDiscussionView.class,
				ProblemDicussionPresenter.IProxy.class);

		bindSingletonPresenterWidget(NewSolutionPresenterWidget.class,
				NewSolutionPresenterWidget.Display.class, NewSolutionView.class);

		bindSingletonPresenterWidget(BrainstormPresenterWidget.class,
				BrainstormPresenterWidget.Display.class, BrainstormView.class);

		bindSingletonPresenterWidget(VotingPresenterWidget.class,
				VotingPresenterWidget.Display.class,
				ProblemVotingView.class);

		bindPresenterWidget(SingleSolutionPresenterWidget.class,
				SingleSolutionPresenterWidget.Display.class,
				SingleSolutionView.class);

		/*
		 * Pop-ups section
		 */
		bindPresenterWidget(LocalDialogPresenterWidget.class,
				LocalDialogPresenterWidget.View.class, LocalDialogView.class);

		/*
		 * Widgets
		 */
		bind(FooterWidget.Binder.class).in(Singleton.class);
		bind(CommentWidget.Binder.class).in(Singleton.class);
		bind(AddCommentBox.Binder.class).in(Singleton.class);
	}

	protected abstract void bindLayoutDependPresenters();
}
