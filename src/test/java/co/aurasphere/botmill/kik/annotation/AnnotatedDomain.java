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
package co.aurasphere.botmill.kik.annotation;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.KeyboardBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.factory.ReplyFactory;
import co.aurasphere.botmill.kik.incoming.event.EventType;
import co.aurasphere.botmill.kik.incoming.event.annotation.BotMillController;
import co.aurasphere.botmill.kik.incoming.event.annotation.BotMillDomain;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.AbstractAnnotatedDomain;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.ResponseType;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;

/**
 * The Class AnnotatedDomain.
 */
@BotMillDomain
public class AnnotatedDomain extends AbstractAnnotatedDomain {

	/**
	 * Reply text.
	 */
	@BotMillController(event = EventType.TEXT_MESSAGE, text = "v")
	public void replyText() {
		reply(new LinkMessageReply() {
			@Override
			public LinkMessage processReply(Message message) {
				return LinkMessageBuilder.getInstance().setTitle("Title").setUrl("http://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.build();
			}
		});
	}
	
	/**
	 * Reply text 1.
	 */
	@BotMillController(event = EventType.TEXT_PATTERN, pattern = "(?i:hi11)")
	public void replyText1() {
		reply(new LinkMessageReply() {
			@Override
			public LinkMessage processReply(Message message) {
				return LinkMessageBuilder.getInstance().setTitle("Title1").setUrl("http://alvinjayreyes.com").setPicUrl("http://pad1.whstatic.com/images/9/9b/Get-the-URL-for-Pictures-Step-2-Version-4.jpg")
						.build();
			}
		});
	}
	
	/**
	 * Reply text 2.
	 */
	@BotMillController(event = EventType.ANY)
	public void replyText2() {
		reply(ReplyFactory.buildTextMessageReply("yeaaasss"));	
	}

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
