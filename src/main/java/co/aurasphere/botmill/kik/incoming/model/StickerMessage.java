package co.aurasphere.botmill.kik.incoming.model;

public class StickerMessage extends IncomingMessage {

    private String stickerPackId;
	private String stickerUrl;
	public String getStickerPackId() {
		return stickerPackId;
	}
	public void setStickerPackId(String stickerPackId) {
		this.stickerPackId = stickerPackId;
	}
	public String getStickerUrl() {
		return stickerUrl;
	}
	public void setStickerUrl(String stickerUrl) {
		this.stickerUrl = stickerUrl;
	}
	
}
