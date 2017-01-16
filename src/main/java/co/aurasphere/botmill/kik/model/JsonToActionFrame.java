package co.aurasphere.botmill.kik.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class JsonToActionFrame {
	
	@SerializedName("jsonkikbotmill")
	private List<JsonTextAction> jsonTextAction;

	public List<JsonTextAction> getJsonTextAction() {
		return jsonTextAction;
	}

	public void setJsonTextAction(List<JsonTextAction> jsonTextAction) {
		this.jsonTextAction = jsonTextAction;
	}
	
	
}
