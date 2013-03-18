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
package com.tdm.client.app.ui.solution;


import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Paweł Doleciński
 * 
 */
public class AddCommentBox extends Composite implements HasKeyDownHandlers,
		HasText {

	public interface Binder extends UiBinder<Widget, AddCommentBox> {
	}

	private class NewCommentHandler implements KeyDownHandler, ChangeHandler {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			resize();
		}

		@Override
		public void onChange(ChangeEvent event) {
			resize();
		}

	}

	@UiField
	TextArea textArea;

	@Inject
	public AddCommentBox(Binder binder) {
		initWidget(binder.createAndBindUi(this));
		NewCommentHandler newCommentHandler = new NewCommentHandler();

		textArea.addKeyDownHandler(newCommentHandler);
		textArea.addChangeHandler(newCommentHandler);
		textArea.getElement().setAttribute("placeholder", "Write a comment...");
	}

	@Override
	public HandlerRegistration addKeyDownHandler(final KeyDownHandler handler) {
		return textArea.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				handler.onKeyDown(event);
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					event.stopPropagation();
					event.preventDefault();
				}
				resize();
			}
		});
	}

	@Override
	public void setText(String string) {
		textArea.setText(string);
	}

	@Override
	public String getText() {
		return textArea.getText();
	}

	private void resize() {
		textArea.setHeight("auto");
		textArea.setHeight(textArea.getElement().getScrollHeight() + "px");
	}

}
