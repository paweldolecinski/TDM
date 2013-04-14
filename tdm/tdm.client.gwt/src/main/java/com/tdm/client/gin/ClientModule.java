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

import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.annotations.GaAccount;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.PresenterSetupModule;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalytics;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalyticsImpl;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalyticsNavigationTracker;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.gwtplatform.mvp.client.proxy.RouteTokenFormatter;
import com.tdm.client.app.BodyPresenter;
import com.tdm.client.gin.ui.ApplicationModule;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

		install(new PresenterSetupModule(DefaultPlaceManager.class,
				RouteTokenFormatter.class));
		
		install(new ClientDispatchModule());

		install(new ApplicationModule());

		install(new ParamsModule());

		bind(RootPresenter.class).to(BodyPresenter.class).asEagerSingleton();

		bind(ResourceLoader.class).asEagerSingleton();

		if (ParamsModule.USE_GA) {
			bindConstant().annotatedWith(GaAccount.class).to(
					ParamsModule.GA_ACCOUNT);
			bind(GoogleAnalytics.class).to(GoogleAnalyticsImpl.class).in(
					Singleton.class);
			bind(GoogleAnalyticsNavigationTracker.class).asEagerSingleton();

		}

	}
}
