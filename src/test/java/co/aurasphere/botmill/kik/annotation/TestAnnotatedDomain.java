package co.aurasphere.botmill.kik.annotation;

import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.KeyboardBuilder;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.ResponseType;
import co.aurasphere.botmill.kik.network.NetworkUtils;

public class TestAnnotatedDomain {

	@Before
	public void setup() {

		KikBotMillContext.getInstance().setup(System.getenv("USERNAME"), System.getenv("APIKEY"));
		NetworkUtils.postJsonConfig(ConfigurationBuilder.getInstance()
				.setWebhook("https://kik-bot-021415.herokuapp.com/kikbot").setManuallySendReadReceipts(false)
				.setReceiveDeliveryReceipts(false).setReceiveIsTyping(true).setReceiveReadReceipts(false)
				.setStaticKeyboard(KeyboardBuilder.getInstance()
						.addResponse(MessageFactory.createResponse("BODY", ResponseType.TEXT))
						.setType(KeyboardType.SUGGESTED).buildKeyboard())
				.buildConfiguration());

	}

	@Test
	public void testAnnotatedDomain() {
		
		//	New
		new AnnotatedDomain();
		
		String json = "{\"messages\": [{\"body\": \"hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi1\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json,MessageCallback.class);
		
		for(Message msg:m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
	}
}
