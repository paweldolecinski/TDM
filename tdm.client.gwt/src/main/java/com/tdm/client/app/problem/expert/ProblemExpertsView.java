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
package com.tdm.client.app.problem.expert;

import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.base.ListItem;
import com.github.gwtbootstrap.client.ui.base.UnorderedList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.tdm.domain.model.expert.dto.ExpertJso;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemExpertsView extends
		ViewWithUiHandlers<ProblemExpertsUiHandlers> implements
		ProblemExpertsPresenter.Display {

	public interface Binder extends UiBinder<Widget, ProblemExpertsView> {
	}

	private final Widget widget;

	@UiField
	protected Paragraph emptyPlaceholder;
	@UiField
	protected Modal inviteModal;
	@UiField
	protected UnorderedList experts;
	@UiField
	protected TextBox mail1;
	@UiField
	protected TextBox mail2;
	@UiField
	protected TextBox mail3;
	@UiField
	protected TextBox mail4;
	@UiField
	protected TextArea msg;

	@Inject
	public ProblemExpertsView(Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiHandler("showModalButton")
	void onClickShowModal(ClickEvent event) {
		inviteModal.show();
	}

	@UiHandler("inviteButton")
	void onClickInvite(ClickEvent event) {
		ArrayList<String> mails = new ArrayList<String>();
		String m1 = mail1.getText();
		if (m1 != null && !m1.isEmpty()) {
			mails.add(m1);
		}
		String m2 = mail2.getText();
		if (m2 != null && !m2.isEmpty()) {
			mails.add(m2);
		}
		String m3 = mail3.getText();
		if (m3 != null && !m3.isEmpty()) {
			mails.add(m3);
		}
		String m4 = mail4.getText();
		if (m4 != null && !m4.isEmpty()) {
			mails.add(m4);
		}
		getUiHandlers().invite(mails, msg.getText());
	}

	@UiHandler("cancelButton")
	void onClickCancel(ClickEvent event) {
		closeModal();
	}

	@Override
	public void clearList() {
		experts.clear();
		emptyPlaceholder.setVisible(true);
	}

	@Override
	public void addExpert(ExpertJso expert) {
		ListItem e = new ListItem();
		HTML htmlPanel = new HTML();
		htmlPanel.setText(expert.getUserId() + " : " + expert.getRole());
		e.add(htmlPanel);
		experts.add(e);
		if (experts.getWidgetCount() > 0) {
			emptyPlaceholder.setVisible(false);
		} else {
			emptyPlaceholder.setVisible(true);
		}
	}

	@Override
	public void mailsSent() {
		closeModal();
	}

	void closeModal() {
		mail1.setText(null);
		mail2.setText(null);
		mail3.setText(null);
		mail4.setText(null);
		msg.setText(null);
		inviteModal.hide();
	}
}
