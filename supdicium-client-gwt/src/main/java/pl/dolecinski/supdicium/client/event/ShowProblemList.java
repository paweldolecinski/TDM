package pl.dolecinski.supdicium.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class ShowProblemList {

	@Order(1)
	String filter;
}
