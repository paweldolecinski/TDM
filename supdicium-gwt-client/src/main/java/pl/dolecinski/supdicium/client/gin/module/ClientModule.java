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
package pl.dolecinski.supdicium.client.gin.module;

import pl.dolecinski.supdicium.client.bl.ClientPlaceManager;
import pl.dolecinski.supdicium.client.vo.problem.model.ProblemInfoProvider;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.annotations.GaAccount;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalyticsNavigationTracker;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install((new DefaultModule(ClientPlaceManager.class)));

		bind(ProblemInfoProvider.class).in(Singleton.class);
		
		if (ParamsModule.USE_GA) {
			bindConstant().annotatedWith(GaAccount.class).to(
					ParamsModule.GA_ACCOUNT);

			bind(GoogleAnalyticsNavigationTracker.class).asEagerSingleton();
		}

	}
}
