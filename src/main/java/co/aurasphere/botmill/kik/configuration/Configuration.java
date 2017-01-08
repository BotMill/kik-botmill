package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;

import co.aurasphere.botmill.kik.incoming.model.ScanDataMessage;
import co.aurasphere.botmill.kik.model.Message;

public class Configuration implements Serializable  {

	private String webhook;
	private Features features;
	private Keyboard keyboard;
	
	public Keyboard getKeyBoard() {
		return keyboard;
	}
	public void setKeyBoard(Keyboard keyboard) {
		this.keyboard = keyboard;
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
