package co.aurasphere.botmill.kik.annotation;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Before;
import org.junit.Test;

import co.aurasphere.botmill.core.BotDefinition;
import co.aurasphere.botmill.core.internal.util.ConfigurationUtils;
import co.aurasphere.botmill.kik.bots.AnnotatedDomain;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.builder.KeyboardBuilder;
import co.aurasphere.botmill.kik.factory.MessageFactory;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.util.json.JsonUtils;

public class IncomingToOutgoingMessageHandlerTest {

	@Before
	public void setup() {

		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("password"); // can be sourced out
		ConfigurationUtils.loadEncryptedConfigurationFile(enc, "botmill.properties");
		List<BotDefinition> botDefinitions = new ArrayList<BotDefinition>();
		botDefinitions.add(new AnnotatedDomain());
		ConfigurationUtils.setBotDefinitionInstance(botDefinitions);
		
		ConfigurationBuilder.getInstance().setWebhook("https://kik-bot-021415.herokuapp.com/kikbot")
				.setManuallySendReadReceipts(false).setReceiveDeliveryReceipts(false).setReceiveIsTyping(true)
				.setReceiveReadReceipts(false)
				.setStaticKeyboard(KeyboardBuilder.getInstance().addResponse(MessageFactory.createTextResponse("BODY"))
						.setType(KeyboardType.SUGGESTED).buildKeyboard())
				.buildConfiguration();

	}

	@Test
	public void testAnnotationRuntime() {
		
		String json = "{\"messages\": [{\"body\": \"Hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi1\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
		MessageCallback m = JsonUtils.fromJson(json, MessageCallback.class);
		for (Message msg : m.getMessages()) {
			IncomingToOutgoingMessageHandler.createHandler().process(msg);
		}
	}
	
	public static void main(String[] args) {
		ConfigurationUtils.loadEncryptedConfigurationProperties();
		ConfigurationUtils.loadBotDefinitions();
		for (int i = 0; i < 500; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					String json = "{\"messages\": [{\"body\": \"Hi\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"},{\"body\": \"hi1\", \"from\": \"alvinpreyes\", \"timestamp\": 1484181332091, \"mention\": null, \"participants\": [\"alvinpreyes\"], \"readReceiptRequested\": true, \"type\": \"text\", \"id\": \"0d1c6c95-f155-45b6-84bd-824323359b56\", \"chatId\": \"35301de98509f5ec304818f79d37d63725e2dfaeef473aff76ae48d5d8a404a3\"}]}";
					MessageCallback m = JsonUtils.fromJson(json, MessageCallback.class);
					for (Message msg : m.getMessages()) {
						IncomingToOutgoingMessageHandler.createHandler().process(msg);
					}
				}
			}).start();
		}
	}
}
