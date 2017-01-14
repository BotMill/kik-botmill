package co.aurasphere.botmill.kik.factory;

import co.aurasphere.botmill.kik.builder.TextMessageBuilder;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;
import co.aurasphere.botmill.kik.reply.TextMessageReply;

public class ReplyFactory {
	public static TextMessageReply buildTextMessageReply(String text){
		return new TextMessageReply() {
			@Override
			public TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody(text).build();
			}
		};
	}
}
