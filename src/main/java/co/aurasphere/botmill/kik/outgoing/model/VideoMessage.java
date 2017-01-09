package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.StaticKeyboard;

public class VideoMessage extends OutgoingMessage {
	
	private String videoUrl;
	private boolean loop;
	private boolean muted;
	private boolean autoplay;
	private boolean noSave;
	
	@SerializedName("keyboards")
	private StaticKeyboard keyboard;

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public boolean isMuted() {
		return muted;
	}

	public void setMuted(boolean muted) {
		this.muted = muted;
	}

	public boolean isAutoplay() {
		return autoplay;
	}

	public void setAutoplay(boolean autoplay) {
		this.autoplay = autoplay;
	}

	public boolean isNoSave() {
		return noSave;
	}

	public void setNoSave(boolean noSave) {
		this.noSave = noSave;
	}

	public StaticKeyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(StaticKeyboard keyboard) {
		this.keyboard = keyboard;
	}
}
