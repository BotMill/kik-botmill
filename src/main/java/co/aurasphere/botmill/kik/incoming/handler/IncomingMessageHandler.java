package co.aurasphere.botmill.kik.incoming.handler;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.intf.Frame;
import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessagePostback;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
public class IncomingMessageHandler {
	
	private static IncomingMessageHandler instance;

	public static IncomingMessageHandler createHandler() {
		if (instance == null) {
			instance = new IncomingMessageHandler();
		}
		return instance;
	}

	@SuppressWarnings({ "incomplete-switch", "unchecked" })
	public IncomingMessageHandler process(Message message) {
		
		switch (message.getType()) {
		case TEXT:
			System.out.println("TEXT");
			MessagePostback a = null;
			for(Frame frame : KikBotMillContext.getInstance().getTextMessageActionFrames()) {
				if(frame.getEvent().verifyEvent(message)) {
					a = new MessagePostback();
					for(Reply<? extends Message> reply:frame.getReplies()) {
						System.out.println(((TextMessage)message).getBody());
						co.aurasphere.botmill.kik.outgoing.model.TextMessage tm = new co.aurasphere.botmill.kik.outgoing.model.TextMessage();
						System.out.println(tm);
						tm = (co.aurasphere.botmill.kik.outgoing.model.TextMessage)reply.processReply((TextMessage)message);
						System.out.println(tm);
						System.out.println(tm.getBody());
						tm.setTo(((TextMessage)message).getFrom());
						tm.setChatId(message.getChatId());
						System.out.println(JsonUtils.toJson(tm));
						a.addMessage(tm);
					}
					break;
				}
			}
			if(a != null) {
				System.out.println("____---" + JsonUtils.toJson(a));
				NetworkUtils.postJsonMessage(a);
			}
			break;
		case PICTURE:
			System.out.println("PIC");
			break;
		case VIDEO:
			System.out.println("VIDEO");
			break;
		case LINK:
			System.out.println("LINK");
			break;
		case SCAN_DATA:
			System.out.println("SCAN");
			break;
		case STICKER:
			System.out.println("STICK");
			break;
		case FRIEND_PICKER:
			System.out.println("FPICKER");
			break;
		}

		return this;
	}
}
