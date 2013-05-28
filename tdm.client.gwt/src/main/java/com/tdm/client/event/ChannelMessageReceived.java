package com.tdm.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class ChannelMessageReceived {
	@Order(1)
	String msg;
}
