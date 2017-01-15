package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.LinkMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class LinkMessageEvent  implements Event<LinkMessage> {
	@Override
	public boolean verifyEvent(LinkMessage incomingMessage) {
		// TODO Auto-generated method stub
		return false;
	}
}
