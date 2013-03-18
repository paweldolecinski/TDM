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

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.tdm.client.app.ui.WidgetList;
import com.tdm.client.app.ui.solution.AddCommentBox;
import com.tdm.client.app.ui.solution.CommentWidget;
import com.tdm.client.resources.AppResources;

/**
 * @author Paweł Doleciński
 * 
 */
public class SingleSolutionView extends
		ViewWithUiHandlers<SingleSolutiontUiHandlers> implements
		SingleSolutionPresenterWidget.Display {

	public interface Binder extends UiBinder<Widget, SingleSolutionView> {
	}

	@UiField(provided = true)
	AddCommentBox newComment;

	@UiField
	WidgetList commentList;
	@UiField
	Hyperlink solutionText;
	@UiField
	InlineLabel solutionDescription;
	@UiField
	DateLabel solutionDate;

	private final Widget widget;

	private final Provider<CommentWidget> commentWidgetProvider;

	private AppResources resources;

	@Inject
	public SingleSolutionView(Binder binder, AppResources resources,
			final Provider<CommentWidget> commentWidgetProvider,
			final AddCommentBox commentBox) {
		this.resources = resources;
		this.commentWidgetProvider = commentWidgetProvider;
		this.newComment = commentBox;

		widget = binder.createAndBindUi(this);
		newComment.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String text = newComment.getText();
					if (!"".equals(text)) {
						getUiHandlers().addNewComment(text);
						newComment.setText("");
					}
				}
			}
		});
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void createNewComment(String text, Date date) {
		CommentWidget commentWidget = commentWidgetProvider.get();
		commentWidget.setText(text);
		commentWidget.setValue(date);
		commentList.add(commentWidget);
	}

	@Override
	public void setText(String text) {
		solutionText.setText(text);
	}

	@Override
	public void setAdditionalText(String text) {
		solutionDescription.setText(text);
	}

	@Override
	public void setDate(Date date) {
		solutionDate.setValue(date);
	}

	@UiFactory
	WidgetList makeWidgetList() {
		return new WidgetList(resources);
	}
}
