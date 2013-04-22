package com.tdm.client.gin.ui;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.tdm.client.app.home.ProblemThumbnailWidget;
import com.tdm.client.app.problem.SolutionIdeaWidget;
import com.tdm.client.app.ui.FooterWidget;

public class UiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/*
		 * Widgets
		 */
		bind(FooterWidget.Binder.class).in(Singleton.class);

		bind(ProblemThumbnailWidget.class).toProvider(
				ProblemThumbnailWidget.Provider.class);

		bind(SolutionIdeaWidget.class).toProvider(
				SolutionIdeaWidget.Provider.class);

	}

}
