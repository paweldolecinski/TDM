package com.tdm.client.app.home;

import com.gwtplatform.mvp.client.UiHandlers;

public interface NewProblemUiHandlers extends UiHandlers {

	void createProblem(String title, String description);

}
