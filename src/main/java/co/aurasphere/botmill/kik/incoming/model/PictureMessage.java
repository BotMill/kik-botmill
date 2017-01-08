package co.aurasphere.botmill.kik.incoming.model;

import co.aurasphere.botmill.kik.model.Attribution;

public class PictureMessage extends IncomingMessage {
	private String picUrl;
	private Attribution attribution;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Attribution getAttribution() {
		return attribution;
	}
	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}
	
}
