package com.tdm.client.app.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.tdm.client.place.NameTokens;
import com.tdm.client.resources.AppResources;
import com.tdm.domain.model.problem.jso.GdmProblemJso;

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
    protected ParagraphElement description;

    private PlaceManager placeManager;

    private AppResources resources;

    private ProblemListItemWidget(PlaceManager placeManager,
	    AppResources resources) {
	this.resources = resources;
	initWidget(binder.createAndBindUi(this));
	this.placeManager = placeManager;
    }

    public void init(GdmProblemJso problem) {
	title.setText(problem.getName());

	if (problem.getId() != null) {
	    String token = placeManager.buildHistoryToken(new PlaceRequest(
		    NameTokens.problem).with(NameTokens.Params.id, problem
		    .getId().getIdString()));

	    title.setTargetHistoryToken(token);
	    titleImage.setTargetHistoryToken(token);
	}
	
	titleImage.getElement().setAttribute(
		"style",
		"background-image:url("
			+ resources.problemDefaultImage().getSafeUri()
				.asString() + "); background-size: cover;");

	description.setInnerText(problem.getDescription());

    }
}
