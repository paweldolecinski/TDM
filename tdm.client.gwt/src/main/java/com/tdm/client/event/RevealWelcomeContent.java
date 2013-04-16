package com.tdm.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;
import com.gwtplatform.mvp.client.Presenter;

@GenEvent
public class RevealWelcomeContent {

	@Order(1)
	Presenter<?, ?> content;
}
