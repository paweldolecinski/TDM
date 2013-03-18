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

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class SingleSolutionPresenterWidget extends
		PresenterWidget<SingleSolutionPresenterWidget.Display> implements
		SingleSolutiontUiHandlers {

	public interface Display extends View,
			HasUiHandlers<SingleSolutiontUiHandlers> {
		void createNewComment(String text, Date date);

		void setText(String text);

		void setAdditionalText(String text);

		void setDate(Date date);
	}

	@Inject
	public SingleSolutionPresenterWidget(EventBus eventBus, Display view,
			PlaceManager placeManager) {
		super(eventBus, view);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void addNewComment(String commentText) {
		getView().createNewComment(commentText, new Date());
	}

	public void setSolutionText(String text) {
		getView().setText(text);
	}

	public void setSolutionAdditionalText(String text) {
		getView().setAdditionalText(text);
	}

	public void setSolutionDate(Date date) {
		getView().setDate(date);
	}
}
