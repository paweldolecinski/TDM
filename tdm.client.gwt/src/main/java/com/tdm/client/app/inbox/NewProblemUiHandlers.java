package com.tdm.client.app.inbox;

import com.gwtplatform.mvp.client.UiHandlers;

public interface NewProblemUiHandlers extends UiHandlers {

	void createProblem(String title, String description);

}
