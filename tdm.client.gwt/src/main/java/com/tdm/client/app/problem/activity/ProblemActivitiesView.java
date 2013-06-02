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
package com.tdm.client.app.problem.activity;

import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.base.ListItem;
import com.github.gwtbootstrap.client.ui.base.UnorderedList;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.tdm.domain.model.idea.dto.SolutionIdea;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemActivitiesView extends ViewImpl implements
		ProblemActivitiesPresenter.Display {

	public interface Binder extends UiBinder<Widget, ProblemActivitiesView> {
	}

	private final Widget widget;

	@UiField
	protected UnorderedList ranking;
	@UiField
	protected Paragraph emptyPlaceholder;

	@Inject
	public ProblemActivitiesView(Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void clearRanking() {
		ranking.clear();
		emptyPlaceholder.setVisible(true);
	}

	@Override
	public void addToRanking(SolutionIdea solutionIdea) {
		ListItem e = new ListItem();
		HTML htmlPanel = new HTML();
		htmlPanel.setText(solutionIdea.getName());
		e.add(htmlPanel);
		ranking.add(e);
		if (ranking.getWidgetCount() > 0) {
			emptyPlaceholder.setVisible(false);
		} else {
			emptyPlaceholder.setVisible(true);
		}
	}
}
