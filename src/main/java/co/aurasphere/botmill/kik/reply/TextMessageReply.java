package co.aurasphere.botmill.kik.reply;

import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;

public abstract class TextMessageReply implements Reply<TextMessage> {
	protected TextMessage textMessage;
}
