package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.StartChattingMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class StartChattingEvent  implements Event<StartChattingMessage>{
	@Override
	public boolean verifyEvent(StartChattingMessage incomingMessage) {
		// TODO Auto-generated method stub
		return false;
	}
}
