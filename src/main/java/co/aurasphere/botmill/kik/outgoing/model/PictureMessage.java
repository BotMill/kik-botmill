package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;
import co.aurasphere.botmill.kik.model.Attribution;

public class PictureMessage extends OutgoingMessage {
	private String picUrl;
	@SerializedName("keyboards")
	private Keyboard keyboard;
	private Attribution attribution;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Keyboard getKeyboard() {
		return keyboard;
	}
	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	public Attribution getAttribution() {
		return attribution;
	}
	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}
	
	
}
