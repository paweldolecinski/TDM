package com.tdm.client.app;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.gwtplatform.mvp.client.PreBootstrapper;

public class PreBootstrapperImpl implements PreBootstrapper {

	protected static final Logger log = Logger
			.getLogger(PreBootstrapperImpl.class.getName());

	@Override
	public void onPreBootstrap() {
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
