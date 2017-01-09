package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private String body;
	private ResponseType type;
	
	public Response(String body, ResponseType type) {
		this.body = body;
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ResponseType getType() {
		return type;
	}
	public void setType(ResponseType type) {
		this.type = type;
	}
	
	
}
