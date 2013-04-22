package com.tdm.client.app.problem;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProblemProcessUiHandlers extends UiHandlers {

    void createSolutionIdea(String text);
    
    void refreshSolutionIdeaList();
}
