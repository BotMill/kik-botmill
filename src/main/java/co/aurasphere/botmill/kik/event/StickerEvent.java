package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.StickerMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class StickerEvent  implements Event<StickerMessage> {
	@Override
	public boolean verifyEvent(StickerMessage incomingMessage) {
		return false;
	}
}
