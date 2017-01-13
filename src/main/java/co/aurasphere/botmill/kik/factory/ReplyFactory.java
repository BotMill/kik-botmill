package co.aurasphere.botmill.kik.factory;

import co.aurasphere.botmill.kik.reply.TextMessageReply;

public class ReplyFactory {
	
	public static TextMessageReply reply(){
		return new TextMessageReply();
	}
}
