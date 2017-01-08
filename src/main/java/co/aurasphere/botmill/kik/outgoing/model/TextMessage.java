package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;

public class TextMessage extends OutgoingMessage {
	
	private String typeTime;
	
	@SerializedName("keyboards")
	private Keyboard keyboard;

	public String getTypeTime() {
		return typeTime;
	}

	public void setTypeTime(String typeTime) {
		this.typeTime = typeTime;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
}
