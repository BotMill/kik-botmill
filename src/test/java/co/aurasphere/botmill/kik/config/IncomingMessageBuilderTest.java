package co.aurasphere.botmill.kik.config;

import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.builder.PictureMessageBuilder;
import co.aurasphere.botmill.kik.builder.TextMessageBuilder;
import co.aurasphere.botmill.kik.configuration.KeyboardType;
import co.aurasphere.botmill.kik.configuration.ResponseType;
import co.aurasphere.botmill.kik.factory.ConfigurationFactory;
import co.aurasphere.botmill.kik.factory.EventFactory;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.MessageEnvelope;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;
import co.aurasphere.botmill.kik.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.reply.TextMessageReply;

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
	public void testJsonPictureMessageParse() {
	
		ActionFrameBuilder.createAction()
		.setEvent(EventFactory.picture()) // user sent "hi"
		.addReply(new TextMessageReply() {
			
			@Override
			public co.aurasphere.botmill.kik.outgoing.model.TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody("Choose a letter Mr. Alvin")
						.addKeyboard()
							.addResponse(ConfigurationFactory.createResponse("A", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
							.addResponse(ConfigurationFactory.createResponse("B", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
							.addResponse(ConfigurationFactory.createResponse("C", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
						.endKeyboard()
						.build();
			}
		})
		.addReply(new PictureMessageReply() {
			@Override
			public PictureMessage processReply(Message message) {
				return PictureMessageBuilder.getInstance().setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.addKeyboard()
							.addResponse(ConfigurationFactory.createResponse("A", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
							.addResponse(ConfigurationFactory.createResponse("B", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
							.addResponse(ConfigurationFactory.createResponse("C", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
						.endKeyboard()
						.build();
			}
		})
		.buildToContext();

		String json = "{\"messages\": [{\"attribution\": {\"iconUrl\": \"https://storage.googleapis.com/bot-dashboard.appspot.com/hosted-images/ba6e21035b07b1adff974d092d21b53e\", \"style\": \"overlay\", \"name\": \"Gallery\"}, \"from\": \"alvinpreyes\", \"timestamp\": 1484455590537, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"picUrl\": \"https://platform.kik.com/content/files/ad6caf91-c2ba-4f24-ac04-17b4f038e38d?t=LL1jCOH6hddVnfIuWomqvHO0EQF8FJIKYwOqgnHw5pSlKZVpalJJTALP7aY9RAjedoxToQBSffJAxX8AMnK2CymSmS8W1qVR1gS9u66uFMGsa15d-2VMMv2WdeHKrgkx\", \"readReceiptRequested\": true, \"type\": \"picture\", \"id\": \"a125ba30-ae34-481f-81b6-e7cb33680c30\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
	}
	
	@Test
	public void testJsonTextMessageParse() {
	
		ActionFrameBuilder.createAction()
		.setEvent(EventFactory.textMessage("hi")) // user sent "hi"
		.addReply(new TextMessageReply() {
			
			@Override
			public co.aurasphere.botmill.kik.outgoing.model.TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody("Choose a letter Mr. Alvin")
						.addKeyboard()
							.addResponse(ConfigurationFactory.createResponse("A", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
							.addResponse(ConfigurationFactory.createResponse("B", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
							.addResponse(ConfigurationFactory.createResponse("C", ResponseType.TEXT))
							.setType(KeyboardType.SUGGESTED)
						.endKeyboard()
						.build();
			}
		})
		.buildToContext();
		

		String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
	}
	
	@Test
	public void testJsonLinkMessageParse() {
	
		ActionFrameBuilder.createAction()
		.setEvent(EventFactory.textMessage("hi")) // user sent "hi"
		.addReply(new LinkMessageReply() {
			
			@Override
			public LinkMessage processReply(Message message) {
				return LinkMessageBuilder.getInstance().setTitle("Title").setUrl("http://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.build();
			}
		})
		.buildToContext();
		

		String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
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
