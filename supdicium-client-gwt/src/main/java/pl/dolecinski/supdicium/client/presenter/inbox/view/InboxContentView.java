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
package pl.dolecinski.supdicium.client.presenter.inbox.view;

import pl.dolecinski.supdicium.client.SDStyle;
import pl.dolecinski.supdicium.client.presenter.inbox.InboxPagePresenter;
import pl.dolecinski.supdicium.client.theme.base.MainCss;
import pl.dolecinski.supdicium.client.ui.ProblemListItemWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author Paweł Doleciński
 * 
 */
public class InboxContentView extends ViewWithUiHandlers<ProblemListUiHandlers>
		implements InboxPagePresenter.Display {

	public interface Binder extends UiBinder<Widget, InboxContentView> {
	}

	private final Widget widget;

	@UiField
	protected HTMLPanel headerPanel;
	@UiField
	protected UListElement problemList;
	@UiField
	protected Button createButton;

	@Inject
	public InboxContentView(Binder binder, EventBus eventBus) {
		widget = binder.createAndBindUi(this);
		LIElement liElement = null;
		ProblemListItemWidget item = null;
		
		liElement = Document.get().createLIElement();
		liElement.addClassName(getResources().span4());
		item = new ProblemListItemWidget();
		item.setTitle("ASDASD");
		liElement.appendChild(item.getElement());
		problemList.appendChild(liElement);
		
		liElement = Document.get().createLIElement();
		liElement.addClassName(getResources().span4());
		item = new ProblemListItemWidget();
		item.setTitle("ZXCZXC");
		liElement.appendChild(item.getElement());
		
		problemList.appendChild(liElement);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiFactory
	public MainCss getResources() {
		MainCss mainCss = SDStyle.getTheme().getSDClientBundle().getMainCss();
		mainCss.ensureInjected();
		return mainCss;
	}

}
