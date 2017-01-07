package co.aurasphere.botmill.kik.model;

import java.io.Serializable;

public class Message implements Serializable {
	
	private String chatId;
	private String id;
	private MessageType type;
	private String mention;

}
