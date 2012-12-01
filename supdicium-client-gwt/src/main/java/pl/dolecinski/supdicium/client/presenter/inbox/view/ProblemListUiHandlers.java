package pl.dolecinski.supdicium.client.presenter.inbox.view;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProblemListUiHandlers extends UiHandlers {
	void showDecisionProblem(String problemId);

	void refreshProblemList(String filter);
}
