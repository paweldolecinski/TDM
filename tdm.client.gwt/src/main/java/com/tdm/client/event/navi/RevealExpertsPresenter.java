package com.tdm.client.event.navi;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class RevealExpertsPresenter {
	@Order(1)
	String problemId;
}
