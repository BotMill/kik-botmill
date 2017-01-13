package co.aurasphere.botmill.kik;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.model.Message;

public class MessageCallback implements Serializable{
	
	@SerializedName("messages")
	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
}
