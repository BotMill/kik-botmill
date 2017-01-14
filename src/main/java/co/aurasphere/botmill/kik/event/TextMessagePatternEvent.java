package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;

public class TextMessagePatternEvent implements Event<TextMessage> {
	
	private String keywordPattern;

	public TextMessagePatternEvent() {}
	
	public TextMessagePatternEvent setPattern(String text) {
		this.keywordPattern = text;
		return this;
	}
	
	@Override
	public boolean verifyEvent(TextMessage incomingMessage) {
		// TODO Auto-generated method stub
		return false;
	}
}
