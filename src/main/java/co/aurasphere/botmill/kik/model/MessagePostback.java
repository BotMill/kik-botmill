package co.aurasphere.botmill.kik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MessagePostback implements Serializable{
	
	@SerializedName("messages")
	private List<Message> messages;
	
	public MessagePostback() {
		messages = new ArrayList<Message>();
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}
}
