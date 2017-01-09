package co.aurasphere.botmill.kik.webhook;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ConfigurationBuilder;
import co.aurasphere.botmill.kik.configuration.Configuration;
import co.aurasphere.botmill.kik.configuration.KeyboardType;
import co.aurasphere.botmill.kik.configuration.ResponseType;
import co.aurasphere.botmill.kik.factory.ConfigurationFactory;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.network.NetworkUtils;

public class Main {

	public Main() {
		
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
		
		KikBotMillContext.getInstance().setup("", "");
		TextMessage txtM = new TextMessage();
		txtM.setType(MessageType.READ_RECEIPT);
		txtM.setBody("asdad");
		
		

		System.out.println(JsonUtils.toJson(txtM));
		NetworkUtils.postJsonConfig(txtM);
	}
	public static void main(String[] args) {
		new Main();
	}
}
