/**
 * 
 * MIT License
 *
 * Copyright (c) 2017 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.configuration.Configuration;
import co.aurasphere.botmill.kik.intf.Keyboardable;
import co.aurasphere.botmill.kik.model.BaseBuilder;

public class ConfigurationBuilder extends BaseBuilder implements Keyboardable<ConfigurationBuilder>{
	
	private Configuration config = new Configuration();
	private static ConfigurationBuilder instance;
	private KeyboardBuilder<ConfigurationBuilder> keyboardBuilder;
	
	public static ConfigurationBuilder getInstance() {
		if (instance == null) {
			instance = new ConfigurationBuilder();
		}
		return instance;
	}
	
	public ConfigurationBuilder() {
		this.keyboardBuilder = new KeyboardBuilder<ConfigurationBuilder>(this);
		this.config = new Configuration();
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
	
	@Override
	public KeyboardBuilder<ConfigurationBuilder> addKeyboard() {
		return this.keyboardBuilder;
	}
	
	@Override
	public ConfigurationBuilder endKeyboard() {
		return (ConfigurationBuilder)this.keyboardBuilder.getParentBuilder();
	}
	
	public Configuration buildConfiguration() {
		config.setStaticKeyBoard(this.keyboardBuilder.buildKeyboard());
		return this.config;
	}
	
}
