package co.aurasphere.botmill.kik.config;

import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.factory.EventFactory;
import co.aurasphere.botmill.kik.factory.ReplyFactory;
import co.aurasphere.botmill.kik.incoming.handler.IncomingMessageHandler;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.MessageEnvelope;
import co.aurasphere.botmill.kik.network.NetworkUtils;

public class IncomingMessageBuilderTest {

	@Before
	public void setup() {
		
		KikBotMillContext.getInstance().setup(System.getProperty("USERNAME"), System.getProperty("APIKEY"));
		
		NetworkUtils.postJsonConfig(ConfigurationBuilder.getInstance()
			.setWebhook("https://kik-bot-021415.herokuapp.com/kikbot")
			.setManuallySendReadReceipts(false)
			.setReceiveDeliveryReceipts(false)
			.setReceiveIsTyping(true)
			.setReceiveReadReceipts(false)
			.buildConfiguration());
		
	}
	
	@Test
	public void testJsonMessageParse() {
	
		ActionFrameBuilder.createAction()
		.setEvent(EventFactory.textMessage("hi")) // user sent "hi"
		.addReply(ReplyFactory.buildTextMessageReply(">>> 1"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 2"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 3"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 4"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 5"))
		.buildToContext();

		String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
//		for(Message msg:m.getMessages()) {
//			IncomingMessageHandler.createHandler().process(msg);
//		}
	}
	
	@Test
	public void testJsonParse() {
		//String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		String json = "{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}";
		Message m = JsonUtils.fromJson(json,Message.class);
		switch(JsonUtils.getType(json)){
			case TEXT:
				TextMessage t = JsonUtils.fromJson(json, TextMessage.class);
				System.out.println(t.getBody());
				break;
		}
	}
	@Test
	public void testJsonParseToEnvelope() {
		String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message message:m.getMessages()) {
			MessageEnvelope msgEnv = new MessageEnvelope();
			if(message instanceof TextMessage) {
				msgEnv.setIncomingMessage(message);
				msgEnv.setParticipants(((TextMessage)message).getParticipants());
			}
			msgEnv.setChatId(message.getChatId());
			System.out.println(msgEnv.getChatId());
		}
	}
}
