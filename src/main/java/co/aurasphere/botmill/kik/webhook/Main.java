package co.aurasphere.botmill.kik.webhook;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.MessageType;

public class Main {

	public Main() {
		
		KikBotMillContext.getInstance().setup("", "");
		TextMessage txtM = new TextMessage();
		txtM.setType(MessageType.READ_RECEIPT);
		txtM.setBody("asdad");

		System.out.println(JsonUtils.toJson(txtM));
	}
	public static void main(String[] args) {
		new Main();
	}
}
