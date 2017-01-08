package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;

public class Response implements Serializable {
	
	private String body;
	private String type;
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
