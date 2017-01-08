package co.aurasphere.botmill.kik.incoming.model;


import co.aurasphere.botmill.kik.model.Attribution;

public class VideoMessage extends IncomingMessage {
	private String videoUrl;
	private Attribution attribution;
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public Attribution getAttribution() {
		return attribution;
	}
	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}

}
