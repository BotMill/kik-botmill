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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;

/**
 * The Class BasicOutgoingMessageTest.
 */
public class BasicOutgoingMessageTest {

	/**
	 * Test text message.
	 */
	// Basic Object Test Case.
	@Test
	public void testTextMessage() {

		String txtMessageResp = "{\"body\":\"asdad\",\"to\":\"To\",\"chatId\":\"Chatid\",\"type\":\"read-receipt\"}";
		KikBotMillContext.getInstance().setup("", "");
		TextMessage txtM = new TextMessage();
		txtM.setType(MessageType.READ_RECEIPT);
		txtM.setBody("asdad");
		txtM.setTo("To");
		txtM.setChatId("Chatid");
		System.out.println(JsonUtils.toJson(txtM));

		assertEquals(txtMessageResp, JsonUtils.toJson(txtM));

	}

	/**
	 * Test link message.
	 */
	@Test
	public void testLinkMessage() {
		String linkMessageStr = "{\"url\":\"http://ichef-1.bbci.co.uk\",\"noForward\":false,\"kikJsData\":{},\"body\":\"asdad\",\"to\":\"To\",\"chatId\":\"Chatid\",\"type\":\"link\"}";
		KikBotMillContext.getInstance().setup("", "");
		LinkMessage linkMessage = new LinkMessage();
		linkMessage.setType(MessageType.LINK);
		linkMessage.setBody("asdad");
		linkMessage.setTo("To");
		linkMessage.setChatId("Chatid");
		linkMessage.setUrl("http://ichef-1.bbci.co.uk");
		System.out.println(JsonUtils.toJson(linkMessage));

		assertEquals(linkMessageStr, JsonUtils.toJson(linkMessage));
	}
}
