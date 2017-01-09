package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;

import co.aurasphere.botmill.kik.incoming.model.ScanDataMessage;
import co.aurasphere.botmill.kik.model.Message;

public class Configuration implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String webhook;
	private Features features;
	private StaticKeyboard keyboard;
	
	public Configuration() {
		this.features = new Features();
		this.keyboard = new StaticKeyboard();
	}
	
	public StaticKeyboard getStaticKeyBoard() {
		return keyboard;
	}
	public void setStaticKeyBoard(StaticKeyboard keyboard) {
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
