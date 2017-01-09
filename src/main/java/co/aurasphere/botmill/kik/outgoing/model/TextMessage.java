package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.StaticKeyboard;

public class TextMessage extends OutgoingMessage {
	
	private String typeTime;
	
	@SerializedName("keyboards")
	private StaticKeyboard keyboard;

	public String getTypeTime() {
		return typeTime;
	}

	public void setTypeTime(String typeTime) {
		this.typeTime = typeTime;
	}

	public StaticKeyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(StaticKeyboard keyboard) {
		this.keyboard = keyboard;
	}
	
}
