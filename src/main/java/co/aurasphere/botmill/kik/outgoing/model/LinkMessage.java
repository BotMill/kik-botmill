package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;
import co.aurasphere.botmill.kik.model.Attribution;

public class LinkMessage extends OutgoingMessage {
	
	private String url;
	private String title;
	private String text;
	private String noForward;
	private String kikJsData;
	private Attribution attribution;
	private String picUrl;
	
	@SerializedName("keyboards")
	private Keyboard keyboard;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNoForward() {
		return noForward;
	}

	public void setNoForward(String noForward) {
		this.noForward = noForward;
	}

	public String getKikJsData() {
		return kikJsData;
	}

	public void setKikJsData(String kikJsData) {
		this.kikJsData = kikJsData;
	}

	public Attribution getAttribution() {
		return attribution;
	}

	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}

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
	
	
	
	
}
