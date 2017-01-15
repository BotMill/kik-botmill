package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class AnyEvent implements Event<IncomingMessage>{
	@Override
	public boolean verifyEvent(IncomingMessage incomingMessage) {
		return false;
	}
}
