package co.aurasphere.botmill.kik.model;

import java.io.Serializable;
import java.util.List;

public class MessageEnvelope implements Serializable {
	
	private String chatId;
	private List<String> participants;
	private Message incomingMessage;
	
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public List<String> getParticipants() {
		return participants;
	}
	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	public Message getIncomingMessage() {
		return incomingMessage;
	}
	public void setIncomingMessage(Message incomingMessage) {
		this.incomingMessage = incomingMessage;
	}
	
	
	
	
}
