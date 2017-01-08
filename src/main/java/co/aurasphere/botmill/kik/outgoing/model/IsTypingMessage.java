package co.aurasphere.botmill.kik.outgoing.model;

public class IsTypingMessage extends OutgoingMessage {
	private boolean isTyping;

	public boolean isTyping() {
		return isTyping;
	}

	public void setTyping(boolean isTyping) {
		this.isTyping = isTyping;
	}
	
	
}
