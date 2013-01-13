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

import pl.dolecinski.supdicium.client.bl.NameTokens;
import pl.dolecinski.supdicium.client.presenter.root.RootWindowPresenter;
import pl.dolecinski.supdicium.client.presenter.voting.VotingPresenterWidget;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * @author Paweł Doleciński
 * 
 */
public class ProblemDicussionPresenter extends
		Presenter<ProblemDicussionPresenter.Display, ProblemDicussionPresenter.IProxy> {

	public interface Display extends View {
		void onReveal();
		void onHide();
	}

	@NameToken(NameTokens.problem)
	@ProxyCodeSplit
	public interface IProxy extends ProxyPlace<ProblemDicussionPresenter> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_NewSolution = new Type<RevealContentHandler<?>>();

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_Brainstorm = new Type<RevealContentHandler<?>>();

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_Voting = new Type<RevealContentHandler<?>>();

	private final NewSolutionPresenterWidget newSolution;

	private final BrainstormPresenterWidget brainstorm;

	private final VotingPresenterWidget voting;

	@Inject
	public ProblemDicussionPresenter(EventBus eventBus, Display view, IProxy proxy,
			NewSolutionPresenterWidget newSolution,
			BrainstormPresenterWidget brainstorm,
			VotingPresenterWidget voting) {
		super(eventBus, view, proxy);
		this.newSolution = newSolution;
		this.brainstorm = brainstorm;
		this.voting = voting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.PresenterWidget#onReveal()
	 */
	@Override
	protected void onReveal() {
		super.onReveal();
		setInSlot(TYPE_NewSolution, newSolution);
		setInSlot(TYPE_Brainstorm, brainstorm);
		getView().onReveal();
	}

	
	@Override
	protected void onHide() {
		super.onHide();
		getView().onHide();
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, RootWindowPresenter.TYPE_MainContent,
				this);
	}

}
