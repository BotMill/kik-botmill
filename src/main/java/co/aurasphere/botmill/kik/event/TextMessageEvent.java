package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.intf.Event;

public class TextMessageEvent implements Event {
	private static TextMessageEvent instance;
	public static TextMessageEvent event() {
		if (instance == null) {
			instance = new TextMessageEvent();
		}
		return instance;
	}
}
