package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.FriendPickerMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class FriendPickerEvent  implements Event<FriendPickerMessage> {
	@Override
	public boolean verifyEvent(FriendPickerMessage incomingMessage) {
		// TODO Auto-generated method stub
		return false;
	}
}
