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
package com.tdm.client.app.problem;

import java.util.Date;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.tdm.client.event.NewSolutionEvent;

public class BrainstormPresenterWidget extends
		PresenterWidget<BrainstormPresenterWidget.Display> implements
		BrainstormUiHandlers {

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_Solution = new Type<RevealContentHandler<?>>();
	private final Provider<SingleSolutionPresenterWidget> solutionProvider;

	public interface Display extends View, HasUiHandlers<BrainstormUiHandlers> {
	}

	@Inject
	public BrainstormPresenterWidget(EventBus eventBus, Display view,
			PlaceManager placeManager,
			Provider<SingleSolutionPresenterWidget> solutionProvider) {
		super(eventBus, view);
		this.solutionProvider = solutionProvider;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		addRegisteredHandler(NewSolutionEvent.getType(),
				new NewSolutionEvent.NewSolutionHandler() {

					@Override
					public void onNewSolution(NewSolutionEvent event) {
						SingleSolutionPresenterWidget solution = solutionProvider
								.get();
						solution.setSolutionText(event.getText());
						solution.setSolutionAdditionalText(event
								.getAdditionalText());
						solution.setSolutionDate(new Date());
						addToSlot(TYPE_Solution, solution);
						
					}
				});

	}
}
