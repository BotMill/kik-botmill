package co.aurasphere.botmill.kik.incoming.handler.model;

import com.google.gson.annotations.SerializedName;

public class JsonReply {
	
	
	@SerializedName("type")
	private String type;


	@SerializedName("text")
	private JsonText text;
	
	@SerializedName("picture")
	private JsonPicture picture;
	
	@SerializedName("link")
	private JsonLink link;
	
	@SerializedName("video")
	private JsonVideo video;
	
	@SerializedName("keyboards")
	private JsonKeyboard keyboard;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public JsonText getText() {
		return text;
	}

	public void setText(JsonText text) {
		this.text = text;
	}

	public JsonPicture getPicture() {
		return picture;
	}

	public void setPicture(JsonPicture picture) {
		this.picture = picture;
	}

	public JsonLink getLink() {
		return link;
	}

	public void setLink(JsonLink link) {
		this.link = link;
	}

	public JsonVideo getVideo() {
		return video;
	}

	public void setVideo(JsonVideo video) {
		this.video = video;
	}

	public JsonKeyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(JsonKeyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	
}
