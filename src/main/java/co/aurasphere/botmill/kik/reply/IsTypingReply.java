package co.aurasphere.botmill.kik.reply;

import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage;

public abstract class IsTypingReply implements Reply<IsTypingMessage> {
	@Override
	public IsTypingMessage processReply(Message message) {
		return null;
	}
}
