package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.StaticKeyboard;
import co.aurasphere.botmill.kik.model.Attribution;

public class PictureMessage extends OutgoingMessage {
	private String picUrl;
	@SerializedName("keyboards")
	private StaticKeyboard keyboard;
	private Attribution attribution;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public StaticKeyboard getKeyboard() {
		return keyboard;
	}
	public void setKeyboard(StaticKeyboard keyboard) {
		this.keyboard = keyboard;
	}
	public Attribution getAttribution() {
		return attribution;
	}
	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}
	
	
}
