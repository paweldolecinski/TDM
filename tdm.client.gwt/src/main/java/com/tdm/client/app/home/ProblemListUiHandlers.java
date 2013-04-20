package com.tdm.client.app.home;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProblemListUiHandlers extends UiHandlers {
    void refreshProblemList(String filter);

    void createNewProblem();
}
