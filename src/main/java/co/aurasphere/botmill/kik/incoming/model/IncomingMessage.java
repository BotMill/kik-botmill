package co.aurasphere.botmill.kik.incoming.model;

import java.util.List;

import co.aurasphere.botmill.kik.model.Message;

//	Received message
public class IncomingMessage extends Message {

	private String from;
	private List<String> participants;
	private String body;
	private String timestamp;
	private String readReceiptRequested;
	
	
}
