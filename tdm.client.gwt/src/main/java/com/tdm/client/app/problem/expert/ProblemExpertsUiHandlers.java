package com.tdm.client.app.problem.expert;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProblemExpertsUiHandlers extends UiHandlers {

	void invite(List<String> mails, String msg);

}
