package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;

public class VideoMessage extends OutgoingMessage {
	
	private String videoUrl;
	private boolean loop;
	private boolean muted;
	private boolean autoplay;
	private boolean noSave;
	@SerializedName("keyboards")
	private Keyboard keyboard;
}
