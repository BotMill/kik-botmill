package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;

public class Configuration implements Serializable  {

	private String webhook;
	private Features features;
	private Keyboard staticKeyBoard;
	
	public Keyboard getStaticKeyBoard() {
		return staticKeyBoard;
	}
	public void setStaticKeyBoard(Keyboard staticKeyBoard) {
		this.staticKeyBoard = staticKeyBoard;
	}
	public String getWebhook() {
		return webhook;
	}
	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}
	public Features getFeatures() {
		return features;
	}
	public void setFeatures(Features features) {
		this.features = features;
	}	
}
