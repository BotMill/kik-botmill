package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.configuration.Configuration;
import co.aurasphere.botmill.kik.model.BaseBuilder;

public class ConfigurationBuilder extends BaseBuilder {
	
	private static ConfigurationBuilder instance;
	private Configuration config = new Configuration();
	private StaticKeyboardBuilder staticKeyboardBuilder;
	
	public ConfigurationBuilder() {
		this.staticKeyboardBuilder = new StaticKeyboardBuilder(this);
		this.config = new Configuration();
	}
	
	public static ConfigurationBuilder getInstance() {
		if (instance == null) {
			instance = new ConfigurationBuilder();
		}
		return instance;
	}
	
	public ConfigurationBuilder setWebhook(String webhook) {
		config.setWebhook(webhook);
		return this;
	}
	
	public ConfigurationBuilder setManuallySendReadReceipts(boolean manuallySendReadReceipts) {
		config.getFeatures().setManuallySendReadReceipts(manuallySendReadReceipts);
		return this;
	}
	
	public ConfigurationBuilder setReceiveReadReceipts(boolean receiveReadReceipts) {
		config.getFeatures().setReceiveReadReceipts(receiveReadReceipts);
		return this;
	}
	
	public ConfigurationBuilder setReceiveIsTyping(boolean receiveIsTyping) {
		config.getFeatures().setReceiveIsTyping(receiveIsTyping);
		return this;
	}
	
	public ConfigurationBuilder setReceiveDeliveryReceipts(boolean receiveDeliveryReceipts) {
		config.getFeatures().setReceiveDeliveryReceipts(receiveDeliveryReceipts);
		return this;
	}
	
	public StaticKeyboardBuilder addStaticKeyboard() {
		return this.staticKeyboardBuilder;
	}
	
	public ConfigurationBuilder endStaticKeyboard() {
		return (ConfigurationBuilder)this.staticKeyboardBuilder.getParentBuilder();
	}
	
	public Configuration buildConfiguration() {
		config.setStaticKeyBoard(this.staticKeyboardBuilder.buildKeyboard());
		return this.config;
	}
	
}
