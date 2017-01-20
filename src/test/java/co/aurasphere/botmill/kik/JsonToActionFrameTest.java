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



import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.incoming.handler.JsonToActionFrameHandler;
import co.aurasphere.botmill.kik.model.Frame;
import co.aurasphere.botmill.kik.network.NetworkUtils;

/**
 * The Class JsonToActionFrameTest.
 */
public class JsonToActionFrameTest {

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		
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
	 * Test basic json to action frame.
	 */
	@Test
	public void testBasicJsonToActionFrame() {
		List<Frame> list = new ArrayList<Frame>();
		
		//String json = "{\"jsonkikbotmill\":[{\"type\":\"text\",\"input\":\"1\",\"output\":\"2\"},{\"type\":\"text\",\"input\":\"3\",\"output\":\"4\"}]}";
		
		//JsonToActionFrame a = JsonUtils.fromJson(NetworkUtils.get("http://technowebhub.com/json_sample.json"), JsonToActionFrame.class);
		JsonToActionFrameHandler.jsonToFrameReply("http://technowebhub.com/json_sample_1.json");
		//	loop
//		for(JsonAction jaction : a.getJsonTextAction()) {
//			if(jaction.getEvent().equals("text")) {
//				list.add(ActionFrameBuilder.getInstance().setEvent(EventFactory.textMessage(jaction.getInput()))
//				.addReply(ReplyFactory.buildTextMessageReply(jaction.getOutput()))
//				.build()
//				);
//			}
//		}
		
		assertTrue(true);
		//assertNotNull(a);
		
	}
	
}
