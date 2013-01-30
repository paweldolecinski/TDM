package com.tdm.client.ui;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.tdm.client.SDStyle;
import com.tdm.client.bl.NameTokens;
import com.tdm.client.model.problem.ProblemInfo;
import com.tdm.client.theme.base.MainCss;

public class ProblemListItemWidget extends Composite {

	public static class Provider implements
			com.google.inject.Provider<ProblemListItemWidget> {

		private PlaceManager placeManager;

		@Inject
		public Provider(PlaceManager placeManager) {
			this.placeManager = placeManager;

		}

		@Override
		public ProblemListItemWidget get() {
			return new ProblemListItemWidget(placeManager);
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
	protected ParagraphElement description;

	private PlaceManager placeManager;

	private ProblemListItemWidget(PlaceManager placeManager) {
		initWidget(binder.createAndBindUi(this));
		this.placeManager = placeManager;
	}

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}

	public void init(ProblemInfo problem) {
		title.setText(problem.geTitle());

		String token = placeManager
				.buildHistoryToken(new PlaceRequest(NameTokens.problem).with(
						NameTokens.Params.id, problem.getId()));

		title.setTargetHistoryToken(token);
		titleImage.setTargetHistoryToken(token);
		titleImage.getElement().setAttribute(
				"style",
				"background-image:url("
						+ SDStyle.getTheme().getSDClientBundle()
								.problemDefaultImage().getSafeUri().asString()
						+ "); background-size: cover;");

		description.setInnerText(problem.getDescription());

	}
}
