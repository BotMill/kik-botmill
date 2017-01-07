package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;
import co.aurasphere.botmill.kik.model.Attribution;

public class PictureMessage extends OutgoingMessage {
	private String picUrl;
	@SerializedName("keyboards")
	private Keyboard keyboard;
	private Attribution attribution;
}
