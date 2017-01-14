package co.aurasphere.botmill.kik.reply;

import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageEnvelope;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;

public abstract class LinkMessageReply implements Reply<LinkMessage> {
	@Override
	public LinkMessage processReply(Message message) {
		return null;
	}
}
