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
package co.aurasphere.botmill.kik.bots;

import java.util.HashMap;

import co.aurasphere.botmill.core.annotation.Bot;
import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.KeyboardBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.factory.ReplyFactory;
import co.aurasphere.botmill.kik.incoming.event.KikBotMillEventType;
import co.aurasphere.botmill.kik.incoming.event.annotation.KikBotMillController;
import co.aurasphere.botmill.kik.incoming.event.annotation.KikBotMillInit;
import co.aurasphere.botmill.kik.incoming.event.annotation.TextInputFlow;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.model.KikBot;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.util.json.JsonUtils;
import co.aurasphere.botmill.kik.util.network.NetworkUtils;

/**
 * The Class AnnotatedDomain.
 */
@Bot
public class AnnotatedDomain extends KikBot {
	
	/**
	 * Initialize.
	 */
	@KikBotMillInit(meta="initialization")
	public void initialize() {
		ConfigurationBuilder.getInstance()
			.setWebhook("https://kik-botmill-021415.herokuapp.com/kikbot").setManuallySendReadReceipts(false)
			.setReceiveDeliveryReceipts(false).setReceiveIsTyping(true).setReceiveReadReceipts(false)
			.setStaticKeyboard(KeyboardBuilder.getInstance()
					.addResponse(MessageFactory.createTextResponse("BODY"))
					.setType(KeyboardType.SUGGESTED).buildKeyboard())
			.buildConfiguration();
	}
	
	/**
	 * Reply text.
	 */
	@KikBotMillController(eventType = KikBotMillEventType.TEXT_MESSAGE, text = "Hi")
	@TextInputFlow(groupId="simpleq",flowId="startFlow",to="flow1", isStart = true)
	public void replyText(IncomingMessage message) {
		// execute a single reply
		reply(new LinkMessageReply() {
			public LinkMessage processReply(Message message) { 
				return LinkMessageBuilder.getInstance()
						.setTitle("Howdy!")
						.setUrl("http://alvinpreyes.com")
						.setPicUrl("http://pad1.whstatic.com/images/"
								+ "9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.build();
			}
		});
		
		// batch
		addReply(ReplyFactory.buildTextMessageReply("Batch Reply1"));
		addReply(ReplyFactory.buildTextMessageReply("Batch Reply2"));
		
		// batch execute replies
		executeReplies();
	}
	
	@TextInputFlow(groupId="simpleq",flowId="flow1",from="startFlow",to="flow2",response="What's your name?")
	public void question1(IncomingMessage message) {
		//	catch response here.
	}
	
	@TextInputFlow(groupId="simpleq",flowId="flow2",from="flow1",to="finalFlow",response="What's your email?")
	public void question2(IncomingMessage message) {
		//	catch response here.
		reply(ReplyFactory.buildTextMessageReply("What's your email?"));
	}
	
	@TextInputFlow(groupId="simpleq",flowId="finalFlow", isEnd = true)
	public void question3(IncomingMessage message) {
		reply(ReplyFactory.buildTextMessageReply("Great!"));
	}

	@KikBotMillController(eventType = KikBotMillEventType.ANY)
	public void replyText2(IncomingMessage message) {
		reply(ReplyFactory.buildTextMessageReply("yeaaasss"));	
	}

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		HashMap<String, KikBot> a = new HashMap<String, KikBot>();
		a.put("a", new AnnotatedDomain());
		a.put("b", new AnnotatedDomain());
		System.out.println(a.get("a").hashCode());
		System.out.println(a.get("b").hashCode());
		
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
						.addResponse(MessageFactory.createTextResponse("BODY"))
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
