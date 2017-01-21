/**
 * 
 * MIT License
 *
 * Copyright (c) 2017 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package co.aurasphere.botmill.kik;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.builder.ActionMessageBuilder;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.KeyboardBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.builder.PictureMessageBuilder;
import co.aurasphere.botmill.kik.builder.TextMessageBuilder;
import co.aurasphere.botmill.kik.builder.VideoMessageBuilder;
import co.aurasphere.botmill.kik.configuration.Configuration;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.factory.EventFactory;
import co.aurasphere.botmill.kik.factory.ReplyFactory;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.MessageEnvelope;
import co.aurasphere.botmill.kik.model.ResponseType;
import co.aurasphere.botmill.kik.model.UserProfile;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;
import co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;
import co.aurasphere.botmill.kik.outgoing.model.VideoMessage;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.TextMessageReply;
import co.aurasphere.botmill.kik.retriever.KikUserProfileRetriever;

/**
 * The Class OutgoingMessageBuilderTest.
 */
public class OutgoingMessageBuilderTest {

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		KikBotMillContext.getInstance().setup(System.getenv("USERNAME"), System.getenv("APIKEY"));
		
		NetworkUtils.postJsonConfig(ConfigurationBuilder.getInstance()
				.setWebhook("https://kik-bot-021415.herokuapp.com/kikbot")
				.setManuallySendReadReceipts(false)
				.setReceiveDeliveryReceipts(false)
				.setReceiveIsTyping(true)
				.setReceiveReadReceipts(false)
				.buildConfiguration());
	}
	
	/**
	 * Test config builder json.
	 */
	@Test
	public void testConfigBuilderJson() {
		
		String configStr = "{\"webhook\":\"https://kik-bot-021415.herokuapp.com/kikbot\",\"features\":{\"manuallySendReadReceipts\":true,\"receiveReadReceipts\":true,\"receiveDeliveryReceipts\":true,\"receiveIsTyping\":true},\"staticKeyboard\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"A\",\"type\":\"text\"},{\"body\":\"B\",\"type\":\"text\"}]}}";
		Configuration config = ConfigurationBuilder.getInstance().setWebhook("https://kik-bot-021415.herokuapp.com/kikbot").setManuallySendReadReceipts(true)
				.setReceiveDeliveryReceipts(true).setReceiveReadReceipts(true).setReceiveIsTyping(true).setStaticKeyboard(
						KeyboardBuilder.getInstance()
						.addResponse(MessageFactory.createResponse("BODY", ResponseType.TEXT))
						.setType(KeyboardType.SUGGESTED).buildKeyboard())
				.buildConfiguration(); 
		
		assertNotNull(JsonUtils.toJson(config));
	}

	/**
	 * Test text message builder.
	 */
	@Test
	public void testTextMessageBuilder() {
		
		String txtMessageResp = "{\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"body\":\"11\",\"to\":\"11\",\"type\":\"text\"}";
		TextMessage textMessage = TextMessageBuilder.getInstance().setBody("11").setTo("11").addKeyboard(
				KeyboardBuilder.getInstance()
				.setType(KeyboardType.SUGGESTED)
				.addResponse(MessageFactory.createResponse("A", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("B", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("C", ResponseType.TEXT))
				.addResponse(MessageFactory.createFriendPickerResponse("hello", 0, 4, null))
				.buildKeyboard()
			).build();

		System.out.println(JsonUtils.toJson(textMessage));
	
		assertNotNull(JsonUtils.toJson(textMessage));
	}

	/**
	 * Test link message builder.
	 */
	@Test
	public void testLinkMessageBuilder() {
		String linkMessageRespStr = "{\"url\":\"url\",\"title\":\"title\",\"text\":\"Text\",\"noForward\":false,\"kikJsData\":{\"key\":\"key\",\"value\":\"value\"},\"attribution\":{\"name\":\"name\",\"iconUrl\":\"iconurl\"},\"picUrl\":\"picure url\",\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"type\":\"link\"}";
		LinkMessage linkMessageResp = LinkMessageBuilder.getInstance()
				.setAttribution(MessageFactory.createAttribution("name", "iconurl"))
				.setKikJsData(MessageFactory.createKikJsData()).setNoForward(false)
				.setPicUrl("picure url").setText("Text").setTitle("title").setUrl("url").addKeyboard(
						KeyboardBuilder.getInstance()
						.setType(KeyboardType.SUGGESTED)
						.addResponse(MessageFactory.createResponse("A", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("B", ResponseType.TEXT))
						.addResponse(MessageFactory.createResponse("C", ResponseType.TEXT))
						.addResponse(MessageFactory.createFriendPickerResponse("hello", 0, 4, null))
						.buildKeyboard()
					).build();

		System.out.println(JsonUtils.toJson(linkMessageResp));

		assertNotNull(JsonUtils.toJson(linkMessageResp));
	}

	/**
	 * Test picure message builder.
	 */
	@Test
	public void testPicureMessageBuilder() {
		String pictureMessageStr = "{\"picUrl\":\"\",\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"attribution\":\"gallery\",\"to\":\"\",\"type\":\"picture\"}";
		PictureMessage pictureMessageResp = PictureMessageBuilder.getInstance()
				.addKeyboard(
					KeyboardBuilder.getInstance()
					.setType(KeyboardType.SUGGESTED)
					.addResponse(MessageFactory.createResponse("A", ResponseType.TEXT))
					.addResponse(MessageFactory.createResponse("B", ResponseType.TEXT))
					.addResponse(MessageFactory.createResponse("C", ResponseType.TEXT))
					.addResponse(MessageFactory.createFriendPickerResponse("hello", 0, 4, null))
					.buildKeyboard()
				).setPicUrl("")
				.setTo("").build();

		System.out.println(JsonUtils.toJson(pictureMessageResp));
		
		assertNotNull(JsonUtils.toJson(pictureMessageResp));
	}

	/**
	 * Test video message builder.
	 */
	@Test
	public void testVideoMessageBuilder() {
		String videoMessageStr = "{\"videoUrl\":\"\",\"loop\":true,\"muted\":false,\"autoplay\":false,\"noSave\":false,\"attribution\":\"camera\",\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"type\":\"video\"}";
		VideoMessage videoMessageResp = VideoMessageBuilder.getInstance().addKeyboard(
				KeyboardBuilder.getInstance()
				.setType(KeyboardType.SUGGESTED)
				.addResponse(MessageFactory.createResponse("A", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("B", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("C", ResponseType.TEXT))
				.addResponse(MessageFactory.createFriendPickerResponse("hello", 0, 4, null))
				.buildKeyboard()
			).setVideoUrl("")
				.setLoop(true).build();

		System.out.println(JsonUtils.toJson(videoMessageResp));
		
		assertNotNull(JsonUtils.toJson(videoMessageResp));
	}
	
	/**
	 * Test is typing message builder.
	 */
	@Test
	public void testIsTypingMessageBuilder() {
		String isTypingStr = "{\"isTyping\":true,\"to\":\"to\",\"type\":\"is-typing\"}";
		IsTypingMessage isTyping = ActionMessageBuilder.buildIsTypingMessage("to");
		System.out.println(JsonUtils.toJson(isTyping));
		
		assertEquals(isTypingStr, JsonUtils.toJson(isTyping));
	}

	/**
	 * Test is typing message parse.
	 */
	@Test
	public void testIsTypingMessageParse() {
		//{\"messages\": [{\"isTyping\": true, \"from\": \"alvinpreyes\", \"timestamp\": 1484494789482, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": false, \"type\": \"is-typing\", \"id\": \"ce6c5d52-223e-4335-93df-207a1877adb1\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}
		
		ActionFrameBuilder.getInstance()
			.setEvent(EventFactory.anyEvent())
			.addReply(new LinkMessageReply() {
				
				@Override
				public LinkMessage processReply(Message message) {
					return LinkMessageBuilder.getInstance().setTitle("Title").setUrl("https://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
							.build();
				}
			})
			.buildToContext();
		
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.textMessage("hi")) // user sent "hi"
		.addReply(new TextMessageReply() {
			
			@Override
			public co.aurasphere.botmill.kik.outgoing.model.TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody("Choose a letter Mr. Alvin")
						.addKeyboard(
								KeyboardBuilder.getInstance()
								.setType(KeyboardType.SUGGESTED)
								.addResponse(MessageFactory.createResponse("A", ResponseType.TEXT))
								.addResponse(MessageFactory.createResponse("B", ResponseType.TEXT))
								.addResponse(MessageFactory.createResponse("C", ResponseType.TEXT))
								.addResponse(MessageFactory.createFriendPickerResponse("hello", 0, 4, null))
								.buildKeyboard()
							)
						.build();
			}
		})
		.buildToContext();
		
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.textMessagePattern("(?i:hi)|(?i:hello)|(?i:hey)|(?i:good day)|(?i:home)")) // user sent "hi"
		.addReply(ReplyFactory.buildTextMessageReply(">>> 1"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 2"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 3"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 4"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 5"))
		.buildToContext();
		

		String json = "{\"messages\": [{\"isTyping\": true, \"from\": \"alvinpreyes\", \"timestamp\": 1484494789482, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": false, \"type\": \"is-typing\", \"id\": \"ce6c5d52-223e-4335-93df-207a1877adb1\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
		
		assertNotNull(JsonUtils.toJson(m));
	}
	
	/**
	 * Test read receipt message builder.
	 */
	@Test
	public void testReadReceiptMessageBuilder() {
		String readReceiptStr = "{\"type\":\"read-receipt\"}";
		ReadReceiptMessage readReceiptMsg = ActionMessageBuilder.buildReadReceiptMessage("to", null);
		System.out.println(JsonUtils.toJson(readReceiptMsg));
		
		assertEquals(readReceiptStr, JsonUtils.toJson(readReceiptMsg));
	}
	
	/**
	 * Test json picture message parse.
	 */
	@Test
	public void testJsonPictureMessageParse() {
	
		ActionFrameBuilder.getInstance()
			.setEvent(EventFactory.anyEvent())
			.addReply(new LinkMessageReply() {
				
				@Override
				public LinkMessage processReply(Message message) {
					return LinkMessageBuilder.getInstance().setTitle("Title").setUrl("http://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
							.build();
				}
			})
			.buildToContext();
		
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.textMessage("hi")) // user sent "hi"
		.addReply(new TextMessageReply() {
			
			@Override
			public co.aurasphere.botmill.kik.outgoing.model.TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody("Choose a letter Mr. Alvin")
						.addKeyboard(
								KeyboardBuilder.getInstance()
								.setType(KeyboardType.SUGGESTED)
								.addResponse(MessageFactory.createFriendPickerResponse("hello", 1, 4, null))
								.buildKeyboard()
							)
						.build();
			}
		})
		.buildToContext();
		
		

		String json = "{\"messages\": [{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi1s\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
		
		assertNotNull(JsonUtils.toJson(m));
	}
	
	/**
	 * Test json text message parse.
	 */
	@Test
	public void testJsonTextMessageParse() {
	
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.textMessage("hi1")) // user sent "hi"
		.addReply(new TextMessageReply() {
			
			@Override
			public co.aurasphere.botmill.kik.outgoing.model.TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody("Choose a letter Mr. Alvin")
						.addKeyboard(
								KeyboardBuilder.getInstance()
								.setType(KeyboardType.SUGGESTED)
								.addResponse(MessageFactory.createResponse("A", ResponseType.TEXT))
								.addResponse(MessageFactory.createResponse("B", ResponseType.TEXT))
								.addResponse(MessageFactory.createResponse("C", ResponseType.TEXT))
								.buildKeyboard()
							)
						.build();
			}
		})
		.buildToContext();
		
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.textMessagePattern("(?i:hi)|(?i:hello)|(?i:hey)|(?i:good day)|(?i:home)")) // user sent "hi"
		.addReply(ReplyFactory.buildTextMessageReply(">>> 1"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 2"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 3"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 4"))
		.addReply(ReplyFactory.buildTextMessageReply(">>> 5"))
		.buildToContext();

		

		String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
		
		assertNotNull(JsonUtils.toJson(m));
	}
	
	/**
	 * Test json link message parse.
	 */
	@Test
	public void testJsonLinkMessageParse() {
	
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.textMessage("hi"))
		.addReply(ReplyFactory.buildTypingReply()).buildToContext();
		
		ActionFrameBuilder.getInstance()
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
		
		assertNotNull(JsonUtils.toJson(m));
	}
	
	/**
	 * Test json parse sketch.
	 */
	@Test
	public void testJsonParseSketch() {
		
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.link()) // user sent "hi"
		.addReply(new LinkMessageReply() {
			
			@Override
			public LinkMessage processReply(Message message) {
				return LinkMessageBuilder.getInstance().setTitle("Title").setUrl("http://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.build();
			}
		}).buildToContext();
		
		String json ="{\"messages\": [{\"attribution\": {\"iconUrl\": \"https://storage.googleapis.com/bot-dashboard.appspot.com/hosted-images/769fa107bca6ff777b0d1e63a60a1a0b\", \"style\": \"below\", \"name\": \"Sketch\"}, \"kikJsData\": {\"width\": \"\", \"image\": \"https://cards-sketch.appspot.com/api/painting/ag5zfmNhcmRzLXNrZXRjaHIVCxIIUGFpbnRpbmcYgIDAmvLOkQgM\", \"height\": \"\"}, \"from\": \"alvinpreyes\", \"title\": \"Sketch\", \"url\": \"http://sketch.kik.com/\", \"timestamp\": 1484528280839, \"noForward\": false, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"text\": null, \"readReceiptRequested\": true, \"type\": \"link\", \"id\": \"51889dc5-3d20-4f3f-982b-9a88c3b36f51\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
		
		assertNotNull(JsonUtils.toJson(m));
	}
	
	/**
	 * Test json parse.
	 */
	@Test
	public void testJsonParse() {
		//String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		String json = "{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}";
		switch(JsonUtils.getType(json)){
			case TEXT:
				TextMessage t = JsonUtils.fromJson(json, TextMessage.class);
				System.out.println(t.getBody());
				break;
		default:
			break;
		}
	}
	
	/**
	 * Test json parse to envelope.
	 */
	@Test
	public void testJsonParseToEnvelope() {
		String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message message:m.getMessages()) {
			MessageEnvelope msgEnv = new MessageEnvelope();
			if(message instanceof TextMessage) {
				msgEnv.setIncomingMessage(message);
				msgEnv.setParticipants(((IncomingMessage)message).getParticipants());
			}
			msgEnv.setChatId(message.getChatId());
			System.out.println(msgEnv.getChatId());
		}
		
		assertNotNull(JsonUtils.toJson(m));
	}
	
	/**
	 * Test get user profile.
	 */
	@Test
	public void testGetUserProfile() {
		UserProfile p = KikUserProfileRetriever.getUserProfile("alvinpreyes");
		assertNotNull(p);
	}

}
