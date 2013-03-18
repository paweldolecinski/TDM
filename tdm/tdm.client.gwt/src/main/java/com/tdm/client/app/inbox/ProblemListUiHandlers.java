package com.tdm.client.app.inbox;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProblemListUiHandlers extends UiHandlers {
	void refreshProblemList(String filter);
}
