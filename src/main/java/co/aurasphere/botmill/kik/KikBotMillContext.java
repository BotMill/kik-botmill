package co.aurasphere.botmill.kik;

import co.aurasphere.botmill.kik.configuration.Authentication;

public class KikBotMillContext {
	
	private static KikBotMillContext instance;
	private Authentication authentication;

	public static KikBotMillContext getInstance() {
		if (instance == null) {
			instance = new KikBotMillContext();
		}
		return instance;
	}

	public String getUser(){
		return this.authentication.getUser();
	}
	
	public String getApiKey() {
		return this.authentication.getApiKey();
	}

	public void setup(String username, String apiKey) {
		this.authentication = new Authentication();
		this.authentication.setUser(username);
		this.authentication.setApiKey(apiKey);
		
	}
}