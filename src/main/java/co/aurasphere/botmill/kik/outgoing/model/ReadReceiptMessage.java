package co.aurasphere.botmill.kik.outgoing.model;

import java.util.List;

public class ReadReceiptMessage extends OutgoingMessage {
	private List<String> messageId;

	public List<String> getMessageId() {
		return messageId;
	}
	public void setMessageId(List<String> messageId) {
		this.messageId = messageId;
	}
}
