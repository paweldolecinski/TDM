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
package com.tdm.client.gin;


import com.google.gwt.inject.client.AsyncProvider;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.tdm.client.TdmApp;
import com.tdm.client.presenter.error.ErrorPagePresenter;
import com.tdm.client.presenter.inbox.InboxPagePresenter;
import com.tdm.client.presenter.problem.BrainstormPresenterWidget;
import com.tdm.client.presenter.problem.NewSolutionPresenterWidget;
import com.tdm.client.presenter.problem.ProblemDicussionPresenter;
import com.tdm.client.presenter.problem.SingleSolutionPresenterWidget;
import com.tdm.client.presenter.root.RootWindowPresenter;
import com.tdm.client.presenter.voting.VotingPresenterWidget;
import com.tdm.client.presenter.welcome.FacebookLoginPresenterWidget;
import com.tdm.client.presenter.welcome.GoogleLoginPresenterWidget;
import com.tdm.client.presenter.welcome.HomePresenter;
import com.tdm.client.presenter.welcome.LoginPresenter;
import com.tdm.client.presenter.welcome.WelcomeContentPresenter;
import com.tdm.client.ui.FooterWidget;
import com.tdm.client.ui.solution.AddCommentBox;
import com.tdm.client.ui.solution.CommentWidget;

public interface MainAppInjector extends CommonInjector {

	TdmApp getGaleriaApp();

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
