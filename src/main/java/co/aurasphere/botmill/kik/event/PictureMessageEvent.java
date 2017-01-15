package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.PictureMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class PictureMessageEvent implements Event<PictureMessage> {
	@Override
	public boolean verifyEvent(PictureMessage incomingMessage) {
		if(!incomingMessage.getPicUrl().equals("")) {
			return true;
		}
		return false;
	}
}
