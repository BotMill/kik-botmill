package co.aurasphere.botmill.kik;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.factory.EventFactory;
import co.aurasphere.botmill.kik.factory.ReplyFactory;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.Frame;
import co.aurasphere.botmill.kik.model.JsonTextAction;
import co.aurasphere.botmill.kik.model.JsonToActionFrame;
import co.aurasphere.botmill.kik.network.NetworkUtils;

public class JsonToActionFrameTest {

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
	
	@Test
	public void testBasicJsonToActionFrame() {
		List<Frame> list = new ArrayList<Frame>();
		
		//String json = "{\"jsonkikbotmill\":[{\"type\":\"text\",\"input\":\"1\",\"output\":\"2\"},{\"type\":\"text\",\"input\":\"3\",\"output\":\"4\"}]}";
		
		JsonToActionFrame a = JsonUtils.fromJson(NetworkUtils.get("http://technowebhub.com/json_sample.json"), JsonToActionFrame.class);
		
		//	loop
		for(JsonTextAction jaction : a.getJsonTextAction()) {
			if(jaction.getType().equals("text")) {
				list.add(ActionFrameBuilder.getInstance().setEvent(EventFactory.textMessage(jaction.getInput()))
				.addReply(ReplyFactory.buildTextMessageReply(jaction.getOutput()))
				.build()
				);
			}
		}
		
		assertTrue(list.size() > 0);
		
	}
	
}
