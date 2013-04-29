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

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.ButtonGroup;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Form.SubmitEvent;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.base.UnorderedList;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.tdm.domain.model.idea.dto.SolutionIdea;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemProcessView extends
		ViewWithUiHandlers<ProblemProcessUiHandlers> implements
		ProblemProcessPresenter.Display {

	public interface Binder extends UiBinder<Widget, ProblemProcessView> {
	}

	private final Widget widget;

	@UiField
	protected UnorderedList solutionList;

	@UiField
	protected Form solutionForm;
	@UiField
	protected TextBox solutionText;
	@UiField
	protected Heading solutionCounter;
	@UiField
	protected Heading problemHeader;
	
	private List<SolutionIdeaWidget> ideas = new ArrayList<SolutionIdeaWidget>();
	private Provider<SolutionIdeaWidget> solutionIdeaProvider;

	@Inject
	public ProblemProcessView(Binder binder, EventBus eventBus,
			Provider<SolutionIdeaWidget> solutionIdeaProvider) {
		this.solutionIdeaProvider = solutionIdeaProvider;
		widget = binder.createAndBindUi(this);

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void focus() {
		solutionText.setFocus(true);
	}

	@UiHandler("solutionForm")
	void onSubmit(SubmitEvent event) {
		event.cancel();
		if (solutionText.getText() != null && !solutionText.getText().isEmpty()) {
			getUiHandlers().createSolutionIdea(solutionText.getText());
			solutionText.setText(null);
			focus();
		}

	}

	@Override
	public void addSolutionIdea(SolutionIdea created) {
		SolutionIdeaWidget s = solutionIdeaProvider.get();
		s.init(created);
		if (ideas.isEmpty()) {
			solutionList.add(s);
		} else {
			int widgetIndex = solutionList.getWidgetIndex(ideas.get(0));
			solutionList.insert(s, widgetIndex);
		}
		ideas.add(0, s);
		solutionCounter.setSubtext("(" + ideas.size() + ")");
	}

	@Override
	public void clear() {
		ideas.clear();
		solutionList.clear();
		solutionCounter.setSubtext("(" + ideas.size() + ")");
	}

	@Override
	public void setHeader(String title, String description) {
		problemHeader.setText(title);
		problemHeader.setSubtext(description);
	}
}
