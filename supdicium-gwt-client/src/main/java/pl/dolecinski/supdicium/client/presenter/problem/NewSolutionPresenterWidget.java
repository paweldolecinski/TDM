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
package pl.dolecinski.supdicium.client.presenter.problem;

import pl.dolecinski.supdicium.client.event.NewSolutionEvent;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class NewSolutionPresenterWidget
		extends
		PresenterWidget<NewSolutionPresenterWidget.Display> implements NewSolutionUiHandlers{

	public interface Display extends View, HasUiHandlers<NewSolutionUiHandlers> {
		void resetFields();
	}

	@Inject
	public NewSolutionPresenterWidget(EventBus eventBus, Display view) {
		super(eventBus, view);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void addNewSolution(String text, String additionalText) {
			getView().resetFields();
			NewSolutionEvent.fire(this, text, additionalText);
	}
}
