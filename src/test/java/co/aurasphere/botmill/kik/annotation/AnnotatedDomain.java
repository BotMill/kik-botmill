package co.aurasphere.botmill.kik.annotation;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.KeyboardBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.incoming.event.EventType;
import co.aurasphere.botmill.kik.incoming.event.annotation.BotMillController;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.AbstractAnnotationDomain;
import co.aurasphere.botmill.kik.model.Event;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.ResponseType;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;

public class AnnotatedDomain extends AbstractAnnotationDomain {

	
	@BotMillController(event = EventType.TEXT_MESSAGE, text = "v")
	public void replyText(Event event) {
		reply(event, new LinkMessageReply() {
			@Override
			public LinkMessage processReply(Message message) {
				return LinkMessageBuilder.getInstance().setTitle("Title").setUrl("http://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.build();
			}
		});
	}
	
	@BotMillController(event = EventType.TEXT_PATTERN, pattern = "(?i:hi)")
	public void replyText1(Event event) {
		reply(event, new LinkMessageReply() {
			@Override
			public LinkMessage processReply(Message message) {
				return LinkMessageBuilder.getInstance().setTitle("Title1").setUrl("http://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.build();
			}
		});
	}
	
	public static void main(String[] args) {
		//	setup first.
		KikBotMillContext.getInstance().setup(System.getenv("USERNAME"), System.getenv("APIKEY"));
		
		NetworkUtils.postJsonConfig(ConfigurationBuilder.getInstance()
			.setWebhook("https://kik-bot-021415.herokuapp.com/kikbot")
			.setManuallySendReadReceipts(false)
			.setReceiveDeliveryReceipts(false)
			.setReceiveIsTyping(true)
			.setReceiveReadReceipts(false)
			.setStaticKeyboard(
						KeyboardBuilder.getInstance()
						.addResponse(MessageFactory.createResponse("BODY", ResponseType.TEXT))
						.setType(KeyboardType.SUGGESTED).buildKeyboard())
			.buildConfiguration());
		
		new AnnotatedDomain();
		
		String json = "{\"messages\": [{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi1\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
	}
}
