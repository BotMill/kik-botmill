package co.aurasphere.botmill.kik.config;

import static org.junit.Assert.*;

import org.junit.Test;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.configuration.Configuration;
import co.aurasphere.botmill.kik.configuration.KeyboardType;
import co.aurasphere.botmill.kik.configuration.ResponseType;
import co.aurasphere.botmill.kik.factory.ConfigurationFactory;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;

public class ConfigurationTest {
	
	@Test
	public void testConfigBuilderJson() {
		
		String configStr = "{\"webhook\":\"\",\"features\":{\"manuallySendReadReceipts\":true,\"receiveReadReceipts\":true,\"receiveDeliveryReceipts\":true,\"receiveIsTyping\":true},\"keyboard\":{\"type\":\"suggested\",\"responses\":[{\"body\":\"\",\"type\":\"text\"},{\"body\":\"\",\"type\":\"text\"}]}}";
		Configuration config = ConfigurationBuilder.getInstance()
				.setWebhook("")
				.setManuallySendReadReceipts(true)
				.setReceiveDeliveryReceipts(true)
				.setReceiveReadReceipts(true)
				.setReceiveIsTyping(true)
					.addStaticKeyboard()
						.setType(KeyboardType.SUGGESTED)
						.addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT))
						.addResponse(ConfigurationFactory.createResponse("", ResponseType.TEXT))
					.endStaticKeyboard()
				.buildConfiguration();	// builder everything. return Configuration Object.
			
			System.out.println(JsonUtils.toJson(config));
			
			assertEquals(configStr, JsonUtils.toJson(config));
	}
	
	//	Basic Object Test Case.
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
	@Test
	public void testLinkMessage() {
		String linkMessageStr = "{\"url\":\"http://ichef-1.bbci.co.uk\",\"body\":\"asdad\",\"to\":\"To\",\"chatId\":\"Chatid\",\"type\":\"link\"}";
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
	
	//	Builder Test Cases Cases
}
