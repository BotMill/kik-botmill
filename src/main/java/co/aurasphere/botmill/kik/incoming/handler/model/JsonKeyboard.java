package co.aurasphere.botmill.kik.incoming.handler.model;

import java.util.List;

public class JsonKeyboard {
	
	private String id;
	private String type;
	private List<JsonResponse> responses;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<JsonResponse> getResponses() {
		return responses;
	}
	public void setResponses(List<JsonResponse> responses) {
		this.responses = responses;
	}
	
	
}
