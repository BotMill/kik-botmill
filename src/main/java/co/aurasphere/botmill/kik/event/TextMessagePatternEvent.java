package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.intf.Event;

public class TextMessagePatternEvent implements Event {
	
	private String keywordPattern;

	public TextMessagePatternEvent() {}
	
	public TextMessagePatternEvent setPattern(String text) {
		this.keywordPattern = text;
		return this;
	}
	
	@Override
	public boolean verifyEvent() {
		//define how we need to check this.
		return false;
	}
}
