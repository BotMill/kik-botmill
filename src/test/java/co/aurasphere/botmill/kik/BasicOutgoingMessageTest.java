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
import co.aurasphere.botmill.kik.bots.AnnotatedDomain;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;
import co.aurasphere.botmill.kik.util.json.JsonUtils;

/**
 * The Class BasicOutgoingMessageTest.
 */
public class BasicOutgoingMessageTest {

	@Before
	public void setUp() {
		
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
				.buildConfiguration();
	}
	
	/**
	 * Test text message.
	 */
	// Basic Object Test Case.
	@Test
	public void testTextMessage() {

		String txtMessageResp = "{\"body\":\"asdad\",\"to\":\"To\",\"chatId\":\"Chatid\",\"type\":\"read-receipt\"}";
		TextMessage txtM = new TextMessage();
		txtM.setType(MessageType.READ_RECEIPT);
		txtM.setBody("asdad");
		txtM.setTo("To");
		txtM.setChatId("Chatid");
		System.out.println(JsonUtils.toJson(txtM));

		assertNotNull(JsonUtils.toJson(txtM));

	}

	/**
	 * Test link message.
	 */
	@Test
	public void testLinkMessage() {
		String linkMessageStr = "{\"url\":\"http://ichef-1.bbci.co.uk\",\"noForward\":false,\"kikJsData\":{},\"body\":\"asdad\",\"to\":\"To\",\"chatId\":\"Chatid\",\"type\":\"link\"}";
		LinkMessage linkMessage = new LinkMessage();
		linkMessage.setType(MessageType.LINK);
		linkMessage.setBody("asdad");
		linkMessage.setTo("To");
		linkMessage.setChatId("Chatid");
		linkMessage.setUrl("http://ichef-1.bbci.co.uk");
		System.out.println(JsonUtils.toJson(linkMessage));

		assertNotNull(JsonUtils.toJson(linkMessage));

	}
}
