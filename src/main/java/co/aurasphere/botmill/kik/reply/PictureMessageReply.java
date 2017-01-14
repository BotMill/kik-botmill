package co.aurasphere.botmill.kik.reply;

import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageEnvelope;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;

public abstract class PictureMessageReply implements Reply<PictureMessage> {
	@Override
	public PictureMessage processReply(Message message) {
		return null;
	}
}
