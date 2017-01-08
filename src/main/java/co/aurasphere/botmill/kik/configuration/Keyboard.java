package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;
import java.util.List;

public class Keyboard implements Serializable {
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Response> getResponses() {
		return responses;
	}
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	private String type;
	private List<Response> responses;
}
