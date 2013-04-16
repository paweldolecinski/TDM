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

import java.util.Date;

import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Paweł Doleciński
 * 
 */
public class CommentWidget extends Composite implements HasDirectionalText,
		TakesValue<Date> {

	public interface Binder extends UiBinder<Widget, CommentWidget> {
	}

	@UiField
	protected InlineLabel commentText;
	@UiField
	protected DateLabel commentDate;

	@Inject
	public CommentWidget(Binder binder) {
		initWidget(binder.createAndBindUi(this));
		commentText.setWordWrap(true);
	}

	@Override
	public String getText() {
		return commentText.getText();
	}

	@Override
	public void setText(String text) {
		commentText.setText(text);
	}

	@Override
	public Direction getTextDirection() {
		return commentText.getTextDirection();
	}

	@Override
	public void setText(String text, Direction dir) {
		commentText.setText(text, dir);
	}

	@Override
	public Date getValue() {
		return commentDate.getValue();
	}

	@Override
	public void setValue(Date value) {
		commentDate.setValue(value);
		// <abbr title="Monday, October 31, 2011 at 7:55pm">October
		// 31 at 7:55pm</abbr>
	}

}
