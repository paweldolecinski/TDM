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
package pl.dolecinski.supdicium.client.presenter.inbox;

import pl.dolecinski.supdicium.client.bl.NameTokens;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemList.ProblemListUiHandlers;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class InboxProblemListPresenterWidget extends InboxProblemList implements
		ProblemListUiHandlers {

	private final PlaceManager placeManager;

	@Inject
	public InboxProblemListPresenterWidget(EventBus eventBus,
			InboxProblemList.Display view, PlaceManager placeManager) {
		super(eventBus, view);
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.dolecinski.supdicium.client.presenter.inbox.InboxProblemList.
	 * ProblemListUiHandlers#showDecisionProblem(java.lang.String)
	 */
	@Override
	public void showDecisionProblem(String problemId) {
		placeManager.revealPlace(new PlaceRequest(NameTokens.problem).with(
				NameTokens.Params.id, problemId));
	}
}
