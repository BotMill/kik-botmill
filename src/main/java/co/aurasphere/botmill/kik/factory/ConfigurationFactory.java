package co.aurasphere.botmill.kik.factory;

import co.aurasphere.botmill.kik.configuration.KeyboardType;
import co.aurasphere.botmill.kik.configuration.Response;
import co.aurasphere.botmill.kik.configuration.ResponseType;
import co.aurasphere.botmill.kik.configuration.StaticKeyboard;

public class ConfigurationFactory {
	
	public static Response createResponse(String body, ResponseType responseType) {
		return new Response(body, responseType);
	}
	
	public static StaticKeyboard createStaticKeyboard(KeyboardType keyboardType, Response response) {
		return new StaticKeyboard();
		
	}
	
}
