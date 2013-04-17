package com.tdm.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class ErrorOccured {

	@Order(1)
	Throwable caught;
}
