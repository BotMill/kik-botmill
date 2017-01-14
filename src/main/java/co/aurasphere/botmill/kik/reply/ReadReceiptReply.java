package co.aurasphere.botmill.kik.reply;

import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.model.AbstractReply;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageEnvelope;
import co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage;

public abstract class ReadReceiptReply implements Reply<ReadReceiptMessage> {
	@Override
	public ReadReceiptMessage processReply(Message message) {
		return null;
	}
}
