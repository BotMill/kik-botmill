package co.aurasphere.botmill.kik.incoming.model;


import co.aurasphere.botmill.kik.model.Attribution;

public class LinkMessage extends IncomingMessage {
	
	private String url;
	private String noForward;
	private String kikJsData;
	private Attribution attribution;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
}
