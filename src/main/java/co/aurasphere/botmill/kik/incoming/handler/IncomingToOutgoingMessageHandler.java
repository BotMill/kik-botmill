package co.aurasphere.botmill.kik.incoming.handler;

import java.util.List;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.event.TextMessageEvent;
import co.aurasphere.botmill.kik.intf.Frame;
import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.ActionFrame;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessagePostback;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.incoming.model.LinkMessage;
import co.aurasphere.botmill.kik.incoming.model.PictureMessage;
import co.aurasphere.botmill.kik.incoming.model.VideoMessage;
import co.aurasphere.botmill.kik.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.reply.TextMessageReply;
import co.aurasphere.botmill.kik.reply.VideoMessageReply;
public class IncomingToOutgoingMessageHandler {
	
	private static IncomingToOutgoingMessageHandler instance;

	public static IncomingToOutgoingMessageHandler createHandler() {
		if (instance == null) {
			instance = new IncomingToOutgoingMessageHandler();
		}
		return instance;
	}


	public IncomingToOutgoingMessageHandler process(Message message) {
		//	the responses are on a different bucket 
		switch (message.getType()) {
		case TEXT: // received a text message
			outgoingHandler(KikBotMillContext.getInstance().getTextMessageActionFrames(), message);
			break;
		case PICTURE:
			outgoingHandler(KikBotMillContext.getInstance().getMediaMessageActionFrames(), message);
			break;
		case VIDEO:
			outgoingHandler(KikBotMillContext.getInstance().getMediaMessageActionFrames(), message);
			break;
		case LINK:
			outgoingHandler(KikBotMillContext.getInstance().getLinkMessageActionFrames(), message);
			break;
		default:
			outgoingHandler(KikBotMillContext.getInstance().getActionFrames(), message);
			break;
		}

		return this;
	}
	
	private void outgoingHandler(List<Frame> actionFrames, Message message) {
		MessagePostback postback = null;
		for(Frame frame : actionFrames) {
			if(frame.getEvent().verifyEvent(message)) {
				postback = new MessagePostback();
				for(Reply<? extends Message> reply:frame.getReplies()) {
					if(reply instanceof TextMessageReply) {
						co.aurasphere.botmill.kik.outgoing.model.TextMessage tm = new co.aurasphere.botmill.kik.outgoing.model.TextMessage();
						tm = (co.aurasphere.botmill.kik.outgoing.model.TextMessage)reply.processReply(message);
						tm.setTo(((IncomingMessage)message).getFrom());
						tm.setChatId(message.getChatId());
						postback.addMessage(tm);
					}else if(reply instanceof PictureMessageReply) {
						co.aurasphere.botmill.kik.outgoing.model.PictureMessage tm = new co.aurasphere.botmill.kik.outgoing.model.PictureMessage();
						tm = (co.aurasphere.botmill.kik.outgoing.model.PictureMessage)reply.processReply(message);
						tm.setTo(((IncomingMessage)message).getFrom());
						tm.setChatId(message.getChatId());
						postback.addMessage(tm);
					}else if(reply instanceof LinkMessageReply) {
						co.aurasphere.botmill.kik.outgoing.model.LinkMessage tm = new co.aurasphere.botmill.kik.outgoing.model.LinkMessage();
						tm = (co.aurasphere.botmill.kik.outgoing.model.LinkMessage)reply.processReply(message);
						tm.setTo(((IncomingMessage)message).getFrom());
						tm.setChatId(message.getChatId());
						postback.addMessage(tm);
					}else if(reply instanceof VideoMessageReply) {
						co.aurasphere.botmill.kik.outgoing.model.VideoMessage tm = new co.aurasphere.botmill.kik.outgoing.model.VideoMessage();
						tm = (co.aurasphere.botmill.kik.outgoing.model.VideoMessage)reply.processReply(message);
						tm.setTo(((IncomingMessage)message).getFrom());
						tm.setChatId(message.getChatId());
						postback.addMessage(tm);
					}
				}
				break;
			}
		}
		if(postback != null) {
			NetworkUtils.postJsonMessage(postback);
		}
	}
}
