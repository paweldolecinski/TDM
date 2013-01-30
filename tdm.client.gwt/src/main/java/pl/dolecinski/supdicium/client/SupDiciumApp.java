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
package pl.dolecinski.supdicium.client;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import pl.dolecinski.supdicium.client.gin.MainAppInjector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public abstract class SupDiciumApp {

	// static {
	// Log.setDefaultLevel(getLogLevel(Level.INFO));
	// }

	protected static final Logger log = Logger.getLogger(SupDiciumApp.class
			.getName());

	protected final EventBus eventBus;

	public SupDiciumApp(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public final void run(MainAppInjector injector) {
		init();
		if (!preRun(injector)) {

		}

		final PlaceManager placeManager = injector.getPlaceManager();

		placeManager.revealCurrentPlace();

		if (!postRun(injector)) {

		}

	}

	protected boolean postRun(MainAppInjector injector) {
		return true;
	}

	protected boolean preRun(MainAppInjector injector) {
		return true;
	}

	private void init() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {

				log.log(Level.SEVERE, "Uncaught exception ", e);
				handleUmbrella(e);
			}
		});
	}

	private boolean handleUmbrella(Throwable e) {
		if (e instanceof UmbrellaException) {
			UmbrellaException u = (UmbrellaException) e;
			final Set<Throwable> causes = u.getCauses();
			for (Throwable t : causes) {
				if (e == t)
					continue;
				if (!handleUmbrella(t))
					log.log(Level.SEVERE, "Inner exception ", t);
			}
			return true;
		}
		if (e instanceof com.google.web.bindery.event.shared.UmbrellaException) {
			UmbrellaException u = (UmbrellaException) e;
			final Set<Throwable> causes = u.getCauses();
			for (Throwable t : causes) {
				if (e == t)
					continue;
				if (!handleUmbrella(t))
					log.log(Level.SEVERE, "Inner exception ", t);
			}
			return true;
		}
		return false;
	}
}
