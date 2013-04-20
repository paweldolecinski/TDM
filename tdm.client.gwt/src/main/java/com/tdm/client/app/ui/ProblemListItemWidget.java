package com.tdm.client.app.ui;

import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.tdm.client.place.NameTokens;
import com.tdm.client.resources.AppResources;
import com.tdm.domain.model.problem.dto.Problem;

public class ProblemListItemWidget extends Composite {

	public static class Provider implements
			com.google.inject.Provider<ProblemListItemWidget> {

		private PlaceManager placeManager;
		private AppResources resources;

		@Inject
		public Provider(PlaceManager placeManager, AppResources resources) {
			this.placeManager = placeManager;
			this.resources = resources;

		}

		@Override
		public ProblemListItemWidget get() {
			return new ProblemListItemWidget(placeManager, resources);
		}
	}

	public interface Binder extends UiBinder<Widget, ProblemListItemWidget> {
	}

	private static Binder binder = GWT.create(Binder.class);

	@UiField
	protected InlineHyperlink titleImage;
	@UiField
	protected InlineHyperlink title;
	@UiField
	protected Paragraph description;
	@UiField
	protected InlineLabel creationDate;

	private PlaceManager placeManager;

	private AppResources resources;

	private ProblemListItemWidget(PlaceManager placeManager,
			AppResources resources) {
		this.resources = resources;
		initWidget(binder.createAndBindUi(this));
		this.placeManager = placeManager;
	}

	public void init(Problem problem) {
		title.setText(problem.getName());

		String token = placeManager.buildHistoryToken(new PlaceRequest(
				NameTokens.problem).with(NameTokens.Params.problemId,
				problem.getKey()));

		title.setTargetHistoryToken(token);
		titleImage.setTargetHistoryToken(token);

		String date = DateTimeFormat.getFormat(
				PredefinedFormat.DATE_TIME_MEDIUM).format(
				problem.getCreationDate());
		creationDate.setText(date);
		titleImage.getElement().setAttribute(
				"style",
				"background-image:url("
						+ resources.problemDefaultImage().getSafeUri()
								.asString() + "); background-size: cover;min-height: 170px;display: block;");

		description.setText(problem.getDescription());

	}
}
