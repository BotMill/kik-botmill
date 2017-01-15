package co.aurasphere.botmill.kik.reply;

import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;

public abstract class PictureMessageReply implements Reply<PictureMessage> {
	protected PictureMessage pictureMessage;
}
