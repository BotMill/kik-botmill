package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.configuration.StaticKeyboard;
import co.aurasphere.botmill.kik.configuration.KeyboardType;
import co.aurasphere.botmill.kik.configuration.Response;
import co.aurasphere.botmill.kik.model.BaseBuilder;

public class StaticKeyboardBuilder extends BaseBuilder {
	
	private static StaticKeyboardBuilder instance;
	private StaticKeyboard keyboard;
	private ConfigurationBuilder parentBuilder;

	public static StaticKeyboardBuilder getInstance() {
		if (instance == null) {
			instance = new StaticKeyboardBuilder();
		}
		return instance;
	}
	
	public StaticKeyboardBuilder() {
		 this.keyboard = new StaticKeyboard();
	}
	
	public StaticKeyboardBuilder(ConfigurationBuilder builder) {
		 this.keyboard = new StaticKeyboard();
		 this.parentBuilder = builder;
	}
	
	public StaticKeyboardBuilder setType(KeyboardType type) {
		this.keyboard.setType(type);
		return this;
	}
	public StaticKeyboardBuilder addResponse(Response e) {
		this.keyboard.getResponses().add(e);
		return this;
	}
	
	public ConfigurationBuilder endStaticKeyboard() {
		return this.parentBuilder;
	}

	public StaticKeyboard buildKeyboard() {
		return this.keyboard;
	}
}
