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
package co.aurasphere.botmill.kik.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ActionMessageBuilder;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.builder.PictureMessageBuilder;
import co.aurasphere.botmill.kik.builder.TextMessageBuilder;
import co.aurasphere.botmill.kik.builder.VideoMessageBuilder;
import co.aurasphere.botmill.kik.configuration.Configuration;
import co.aurasphere.botmill.kik.configuration.KeyboardType;
import co.aurasphere.botmill.kik.configuration.ResponseType;
import co.aurasphere.botmill.kik.factory.ConfigurationFactory;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;
import co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;
import co.aurasphere.botmill.kik.outgoing.model.VideoMessage;

public class OutgoingMessageBuilderTest {

	@Before
	public void setUp() {
		KikBotMillContext.getInstance().setup(System.getProperty("USERNAME"), System.getProperty("APIKEY"));
	}
	
	@Test
	public void testConfigBuilderJson() {
		
		String configStr = "{\"webhook\":\"https://example.com/incoming\",\"features\":{\"manuallySendReadReceipts\":true,\"receiveReadReceipts\":true,\"receiveDeliveryReceipts\":true,\"receiveIsTyping\":true},\"staticKeyboard\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"A\",\"type\":\"text\"},{\"body\":\"B\",\"type\":\"text\"}]}}";
		Configuration config = ConfigurationBuilder.getInstance().setWebhook("https://example.com/incoming").setManuallySendReadReceipts(true)
				.setReceiveDeliveryReceipts(true).setReceiveReadReceipts(true).setReceiveIsTyping(true).addKeyboard()
				.setType(KeyboardType.SUGGESTED).addResponse(ConfigurationFactory.createResponse("A", ResponseType.TEXT))
				.addResponse(ConfigurationFactory.createResponse("B", ResponseType.TEXT)).endKeyboard()
				.buildConfiguration(); 

		assertEquals(configStr, JsonUtils.toJson(config));
	}

	@Test
	public void testTextMessageBuilder() {
		String txtMessageResp = "{\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"body\":\"11\",\"to\":\"11\",\"type\":\"text\"}";
		TextMessage textMessage = TextMessageBuilder.getInstance().setBody("11").setTo("11").addKeyboard()
				.setType(KeyboardType.SUGGESTED).addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT))
				.addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT)).endKeyboard().build();

		System.out.println(JsonUtils.toJson(textMessage));
		assertEquals(txtMessageResp, JsonUtils.toJson(textMessage));
	}

	@Test
	public void testLinkMessageBuilder() {
		String linkMessageRespStr = "{\"url\":\"url\",\"title\":\"title\",\"text\":\"Text\",\"noForward\":false,\"kikJsData\":{\"key\":\"key\",\"value\":\"value\"},\"attribution\":{\"name\":\"name\",\"iconUrl\":\"iconurl\"},\"picUrl\":\"picure url\",\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"type\":\"link\"}";
		LinkMessage linkMessageResp = LinkMessageBuilder.getInstance()
				.setAttribution(MessageFactory.createAttribution("name", "iconurl"))
				.setKikJsData(MessageFactory.createKeyValuePair("key", "value")).setNoForward(false)
				.setPicUrl("picure url").setText("Text").setTitle("title").setUrl("url").addKeyboard()
				.setType(KeyboardType.SUGGESTED).addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT))
				.addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT)).endKeyboard().build();

		System.out.println(JsonUtils.toJson(linkMessageResp));
		assertEquals(linkMessageRespStr, JsonUtils.toJson(linkMessageResp));
	}

	@Test
	public void testPicureMessageBuilder() {
		String pictureMessageStr = "{\"picUrl\":\"\",\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"attribution\":\"gallery\",\"to\":\"\",\"type\":\"picture\"}";
		PictureMessage pictureMessageResp = PictureMessageBuilder.getInstance().addKeyboard()
				.setType(KeyboardType.SUGGESTED).addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT))
				.addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT)).endKeyboard().setPicUrl("")
				.setTo("").build();

		System.out.println(JsonUtils.toJson(pictureMessageResp));
		assertEquals(pictureMessageStr, JsonUtils.toJson(pictureMessageResp));
	}

	@Test
	public void testVideoMessageBuilder() {
		String videoMessageStr = "{\"videoUrl\":\"\",\"loop\":true,\"muted\":false,\"autoplay\":false,\"noSave\":false,\"attribution\":\"camera\",\"keyboards\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]},\"type\":\"video\"}";
		VideoMessage videoMessageResp = VideoMessageBuilder.getInstance().addKeyboard().setType(KeyboardType.SUGGESTED)
				.addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT))
				.addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT)).endKeyboard().setVideoUrl("")
				.setLoop(true).build();

		System.out.println(JsonUtils.toJson(videoMessageResp));
		assertEquals(videoMessageStr, JsonUtils.toJson(videoMessageResp));
	}
	
	@Test
	public void testIsTypingMessageBuilder() {
		String isTypingStr = "{\"isTyping\":true,\"to\":\"to\",\"type\":\"is-typing\"}";
		IsTypingMessage isTyping = ActionMessageBuilder.buildIsTypingMessage("to");
		System.out.println(JsonUtils.toJson(isTyping));
		
		assertEquals(isTypingStr, JsonUtils.toJson(isTyping));
	}

	@Test
	public void testReadReceiptMessageBuilder() {
		String readReceiptStr = "{\"type\":\"read-receipt\"}";
		ReadReceiptMessage readReceiptMsg = ActionMessageBuilder.buildReceReceiptMessage("to", null);
		System.out.println(JsonUtils.toJson(readReceiptMsg));
		
		assertEquals(readReceiptStr, JsonUtils.toJson(readReceiptMsg));
	}
	
}
