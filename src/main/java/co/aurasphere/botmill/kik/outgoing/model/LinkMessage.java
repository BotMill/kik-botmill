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
	
	
}
