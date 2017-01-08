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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<String> getParticipants() {
		return participants;
	}
	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getReadReceiptRequested() {
		return readReceiptRequested;
	}
	public void setReadReceiptRequested(String readReceiptRequested) {
		this.readReceiptRequested = readReceiptRequested;
	}	
}
