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
import com.tdm.client.app.error.ErrorPageView;
import com.tdm.client.app.home.HomePresenter;
import com.tdm.client.app.home.HomeView;
import com.tdm.client.app.home.NewProblemPresenterWidget;
import com.tdm.client.app.home.NewProblemView;
import com.tdm.client.app.navibar.NaviBarPresenterWidget;
import com.tdm.client.app.navibar.NaviBarView;
import com.tdm.client.app.problem.ProblemProcessPresenter;
import com.tdm.client.app.problem.ProblemProcessView;
import com.tdm.client.app.problem.activity.ProblemActivitiesPresenter;
import com.tdm.client.app.problem.activity.ProblemActivitiesView;
import com.tdm.client.app.problem.expert.ProblemExpertsPresenter;
import com.tdm.client.app.problem.expert.ProblemExpertsView;
import com.tdm.client.app.welcome.LoginPresenter;
import com.tdm.client.app.welcome.LoginView;

public class ApplicationModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		install(new UiModule());

		bindPresenter(AppPresenter.class, AppPresenter.Display.class,
				AppView.class, AppPresenter.IProxy.class);

		bindPresenter(ErrorPagePresenter.class,
				ErrorPagePresenter.Display.class, ErrorPageView.class,
				ErrorPagePresenter.IProxy.class);

		bindSingletonPresenterWidget(NaviBarPresenterWidget.class,
				NaviBarPresenterWidget.Display.class, NaviBarView.class);
		/*
		 * Welcome section
		 */
		bindPresenter(LoginPresenter.class, LoginPresenter.Display.class,
				LoginView.class, LoginPresenter.IProxy.class);

		/*
		 * Inbox section
		 */
		bindPresenter(HomePresenter.class, HomePresenter.Display.class,
				HomeView.class, HomePresenter.IProxy.class);

		bindSingletonPresenterWidget(NewProblemPresenterWidget.class,
				NewProblemPresenterWidget.Display.class, NewProblemView.class);

		/*
		 * Problem section
		 */
		bindPresenter(ProblemProcessPresenter.class,
				ProblemProcessPresenter.Display.class,
				ProblemProcessView.class, ProblemProcessPresenter.IProxy.class);

		bindPresenter(ProblemActivitiesPresenter.class,
				ProblemActivitiesPresenter.Display.class,
				ProblemActivitiesView.class,
				ProblemActivitiesPresenter.IProxy.class);

		bindPresenter(ProblemExpertsPresenter.class,
				ProblemExpertsPresenter.Display.class,
				ProblemExpertsView.class, ProblemExpertsPresenter.IProxy.class);

	}
}
