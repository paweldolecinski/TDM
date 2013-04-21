package com.tdm.client.gin.ui;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.tdm.client.app.ui.FooterWidget;
import com.tdm.client.app.ui.ProblemListItemWidget;

public class UiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/*
		 * Widgets
		 */
		bind(FooterWidget.Binder.class).in(Singleton.class);

		bind(ProblemListItemWidget.class).toProvider(
				ProblemListItemWidget.Provider.class);
	}

}
