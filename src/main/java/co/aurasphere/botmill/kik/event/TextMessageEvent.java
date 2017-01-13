package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.intf.Event;

public class TextMessageEvent implements Event {
	private String keywordText;
	private String keywordPattern;

	public TextMessageEvent() {}
	
	public TextMessageEvent setText(String text) {
		this.keywordText = text;
		return this;
	}
	
	public TextMessageEvent setPattern(String text) {
		this.keywordPattern = text;
		return this;
	}
	
	@Override
	public boolean verifyEvent() {
		//define how we need to check this.
		return false;
	}
}
