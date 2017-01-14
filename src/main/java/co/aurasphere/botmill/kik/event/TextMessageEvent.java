package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;

public class TextMessageEvent implements Event<TextMessage> {
	private String keywordText;

	public TextMessageEvent() {}
	
	public TextMessageEvent setText(String text) {
		this.keywordText = text;
		return this;
	}
	
	@Override
	public boolean verifyEvent(TextMessage message) {
		if(this.keywordText.equals(message.getBody())) {
			return true;
		}
		return false;
	}
}
