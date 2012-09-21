package pl.dolecinski.supdicium.client.event;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class NewSolution {

	@Order(1)
	String text;
	@Order(2)
	String additionalText;
}
