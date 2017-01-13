package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.intf.Event;

public class TextMessagePatternEvent implements Event {
	private static TextMessagePatternEvent instance;
	public static TextMessagePatternEvent event() {
		if (instance == null) {
			instance = new TextMessagePatternEvent();
		}
		return instance;
	}
}
