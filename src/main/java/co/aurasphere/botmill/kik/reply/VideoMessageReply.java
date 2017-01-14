package co.aurasphere.botmill.kik.reply;

import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.outgoing.model.VideoMessage;

public abstract class VideoMessageReply implements Reply<VideoMessage> {
	
	protected VideoMessage videoMessage;

}
