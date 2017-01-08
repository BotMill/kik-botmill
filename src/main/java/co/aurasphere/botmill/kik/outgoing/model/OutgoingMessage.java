package co.aurasphere.botmill.kik.outgoing.model;

import co.aurasphere.botmill.kik.model.Message;

public class OutgoingMessage extends Message {
	private String body;
	private String to;
	private String delay;
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	
	

}
