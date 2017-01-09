package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StaticKeyboard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public StaticKeyboard() {
		this.responses = new ArrayList<Response>();
	}
	
	public KeyboardType getType() {
		return type;
	}
	public void setType(KeyboardType type) {
		this.type = type;
	}
	public List<Response> getResponses() {
		return responses;
	}
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	private KeyboardType type;
	private List<Response> responses;
}
