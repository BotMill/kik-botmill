package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.ScanDataMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class ScanDataEvent  implements Event<ScanDataMessage> {
	@Override
	public boolean verifyEvent(ScanDataMessage incomingMessage) {
		// TODO Auto-generated method stub
		return false;
	}
}
