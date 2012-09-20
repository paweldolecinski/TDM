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
package pl.dolecinski.supdicium.client.gin;

import pl.dolecinski.supdicium.client.SupDiciumApp;
import pl.dolecinski.supdicium.client.presenter.error.ErrorPagePresenter;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxMenuPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxPagePresenter;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemListPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.problem.BrainstormPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.problem.NewSolutionPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.problem.ProblemDicussionPresenter;
import pl.dolecinski.supdicium.client.presenter.problem.SingleSolutionPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.root.RootWindowPresenter;
import pl.dolecinski.supdicium.client.presenter.voting.VotingPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.welcome.FacebookLoginPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.welcome.GoogleLoginPresenterWidget;
import pl.dolecinski.supdicium.client.presenter.welcome.HomePresenter;
import pl.dolecinski.supdicium.client.presenter.welcome.LoginPresenter;
import pl.dolecinski.supdicium.client.presenter.welcome.WelcomeContentPresenter;
import pl.dolecinski.supdicium.client.ui.FooterWidget;
import pl.dolecinski.supdicium.client.ui.solution.CommentWidget;
import pl.dolecinski.supdicium.client.ui.solution.AddCommentBox;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public interface MainAppInjector extends CommonInjector {

	SupDiciumApp getGaleriaApp();

	TokenFormatter getTokenFormatter();

	/*
	 * Presenters
	 */
	Provider<RootWindowPresenter> getRootWindowPresenter();

	AsyncProvider<ErrorPagePresenter> getErrorPagePresenter();

	/*
	 * Welcome section
	 */
	AsyncProvider<WelcomeContentPresenter> getWelcomeContentPresenter();

	AsyncProvider<LoginPresenter> getLoginPresenter();

	AsyncProvider<GoogleLoginPresenterWidget> getGoogleLoginPresenterWidget();

	AsyncProvider<FacebookLoginPresenterWidget> getFacebookLoginPresenterWidget();

	AsyncProvider<HomePresenter> getHomePresenter();

	/*
	 * Inbox section
	 */
	AsyncProvider<InboxPagePresenter> getInboxContentPresenter();

	AsyncProvider<InboxMenuPresenterWidget> getInboxMenuPresenterWidget();

	AsyncProvider<InboxProblemListPresenterWidget> getInboxProblemListPresenterWidget();

	/*
	 * Problem section
	 */
	AsyncProvider<ProblemDicussionPresenter> getProblemPagePresenter();

	AsyncProvider<NewSolutionPresenterWidget> getNewSolutionPresenterWidget();

	AsyncProvider<BrainstormPresenterWidget> getBrainstormPresenterWidget();

	AsyncProvider<VotingPresenterWidget> getProblemVotingPresenterWidget();

	AsyncProvider<SingleSolutionPresenterWidget> getSingleSolutionPresenterWidget();
	
	/*
	 * Widgets
	 */
	
	CommentWidget getCommentWidget();
	
	AddCommentBox getCommentBox();
	
	FooterWidget getFooterWidget();
}
