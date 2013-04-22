package com.tdm.client.app.home;

import com.gwtplatform.mvp.client.UiHandlers;

public interface HomeUiHandlers extends UiHandlers {
	void refreshProblemList(String filter);
	
    void createNewProblem();
}
