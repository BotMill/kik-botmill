package co.aurasphere.botmill.kik.model;

import java.io.Serializable;

public class Message implements Serializable {
	
	private String chatId;
	private String id;
	private MessageType type;
	private String mention;
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}

}
