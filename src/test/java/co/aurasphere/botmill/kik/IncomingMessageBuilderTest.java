/*
 * 
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
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

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.core.BotDefinition;
import co.aurasphere.botmill.core.internal.util.ConfigurationUtils;
import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.bots.AnnotatedDomain;
import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.KeyboardBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.builder.MetadataBuilder;
import co.aurasphere.botmill.kik.builder.PictureMessageBuilder;
import co.aurasphere.botmill.kik.builder.TextMessageBuilder;
import co.aurasphere.botmill.kik.factory.EventFactory;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.MessageEnvelope;
import co.aurasphere.botmill.kik.model.ResponseType;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.TextMessageReply;
import co.aurasphere.botmill.kik.util.json.JsonUtils;
import co.aurasphere.botmill.kik.util.network.NetworkUtils;

/**
 * The Class IncomingMessageBuilderTest.
 */
public class IncomingMessageBuilderTest {

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("password"); // can be sourced out
		ConfigurationUtils.loadEncryptedConfigurationFile(enc, "botmill.properties");
		List<BotDefinition> botDefinitions = new ArrayList<BotDefinition>();
		botDefinitions.add(new AnnotatedDomain());
		ConfigurationUtils.setBotDefinitionInstance(botDefinitions);
		
		ConfigurationBuilder.getInstance()
			.setWebhook("https://kik-bot-021415.herokuapp.com/kikbot")
			.setManuallySendReadReceipts(false)
			.setReceiveDeliveryReceipts(false)
			.setReceiveIsTyping(true)
			.setReceiveReadReceipts(false)
			.setStaticKeyboard(
						KeyboardBuilder.getInstance()
						.addResponse(MessageFactory.createTextResponse("BODY"))
						.setType(KeyboardType.SUGGESTED).buildKeyboard())
			.buildConfiguration();
		
	}
	
	/**
	 * Test json picture message parse.
	 */
	@Test
	public void testJsonPictureMessageParse() {
	
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.picture()) // user sent "hi"
		.addReply(new TextMessageReply() {
			
			@Override
			public co.aurasphere.botmill.kik.outgoing.model.TextMessage processReply(Message message) {
				
				return TextMessageBuilder.getInstance().setBody("Choose a letter Mr. Alvin")
						.addKeyboard(
						KeyboardBuilder.getInstance()
							.setType(KeyboardType.SUGGESTED)
							.addResponse(MessageFactory.createTextResponse("A"))
							.addResponse(MessageFactory.createTextResponse("B"))
							.addResponse(MessageFactory.createTextResponse("C"))
							.buildKeyboard()
							)
						.build();
			}
		})
		.addReply(new PictureMessageReply() {
			@Override
			public PictureMessage processReply(Message message) {
				return PictureMessageBuilder.getInstance().setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.addKeyboard(
						KeyboardBuilder.getInstance()
							.setType(KeyboardType.SUGGESTED)
							.addResponse(MessageFactory.createTextResponse("A"))
							.addResponse(MessageFactory.createTextResponse("B"))
							.addResponse(MessageFactory.createTextResponse("C"))
							.buildKeyboard()
							)
						.build();
			}
		})
		.buildToContext();

		String json = "{\"messages\": [{\"attribution\": {\"iconUrl\": \"https://storage.googleapis.com/bot-dashboard.appspot.com/hosted-images/ba6e21035b07b1adff974d092d21b53e\", \"style\": \"overlay\", \"name\": \"Gallery\"}, \"from\": \"alvinpreyes\", \"timestamp\": 1484455590537, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"picUrl\": \"https://platform.kik.com/content/files/ad6caf91-c2ba-4f24-ac04-17b4f038e38d?t=LL1jCOH6hddVnfIuWomqvHO0EQF8FJIKYwOqgnHw5pSlKZVpalJJTALP7aY9RAjedoxToQBSffJAxX8AMnK2CymSmS8W1qVR1gS9u66uFMGsa15d-2VMMv2WdeHKrgkx\", \"readReceiptRequested\": true, \"type\": \"picture\", \"id\": \"a125ba30-ae34-481f-81b6-e7cb33680c30\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
		
		assertNotNull(m);
	}
	
	/**
	 * Test json text message parse.
	 */
	@Test
	public void testJsonTextMessageParse() {
		
		ActionFrameBuilder.getInstance()
		.setEvent(EventFactory.textMessage("hi")) // user sent "hi"
		.addReply(new TextMessageReply() {
			@Override
			public co.aurasphere.botmill.kik.outgoing.model.TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody("Choose a letter Mr. Alvin")
						.addKeyboard(
							KeyboardBuilder.getInstance()
								.setType(KeyboardType.SUGGESTED)
								.addResponse(MessageFactory.createPictureResponse(
										"http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg",
											MetadataBuilder.getInstance()
												.addMetadata("product", "1")
												.addMetadata("product_2", "noice")
											.build())
										)
								.buildKeyboard()
								)
						.build();
			}
		})
		.buildToContext();
		
		
		String json = "{\"messages\": [{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi1\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
			IncomingToOutgoingMessageHandler.createHandler().processBroadcast(msg);
		}
		
		assertNotNull(m);
	}
	
	/**
	 * Test json link message parse.
	 */
	@Test
	public void testJsonLinkMessageParse() {
	
		ActionFrameBuilder.getInstance()
			.setEvent(EventFactory.textMessage("hi")) // user sent "hi"
			.addReply(new LinkMessageReply() {
				
				@Override
				public LinkMessage processReply(Message message) {
					return LinkMessageBuilder.getInstance()
							.setTitle("This is a link title")
							.setUrl("http://alvinjayreyes.com")
							.setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
							.build();
				}
			})
			.buildToContext();
		

		String json = "{\"messages\": [{\"body\": \":P\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
		
		assertNotNull(m);
	}
	
	/**
	 * Test json parse.
	 */
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
		
		assertNotNull(m);
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
				msgEnv.setParticipants(((TextMessage)message).getParticipants());
			}
			msgEnv.setChatId(message.getChatId());
			System.out.println(msgEnv.getChatId());
		}
		
		assertNotNull(m);
	}
}
