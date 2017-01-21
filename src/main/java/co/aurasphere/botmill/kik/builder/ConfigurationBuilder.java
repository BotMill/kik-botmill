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
import co.aurasphere.botmill.kik.model.BaseBuilder;
import co.aurasphere.botmill.kik.model.Keyboard;
import co.aurasphere.botmill.kik.network.NetworkUtils;

/**
 * The Class ConfigurationBuilder.
 * 
 * @author Alvin P. Reyes
 */
public class ConfigurationBuilder extends BaseBuilder{
	
	/** The config. */
	private Configuration config = new Configuration();
	
	/** The instance. */
	private static ConfigurationBuilder instance;
	
	/**
	 * Gets the single instance of ConfigurationBuilder.
	 *
	 * @return single instance of ConfigurationBuilder
	 */
	public static ConfigurationBuilder getInstance() {
		if (instance == null) {
			instance = new ConfigurationBuilder();
		}
		return instance;
	}
	
	/**
	 * Instantiates a new configuration builder.
	 */
	public ConfigurationBuilder() {
		this.config = new Configuration();
	}
	
	/**
	 * Sets the webhook.
	 *
	 * @param webhook the webhook
	 * @return the configuration builder
	 */
	public ConfigurationBuilder setWebhook(String webhook) {
		config.setWebhook(webhook);
		return this;
	}
	
	/**
	 * Sets the manually send read receipts.
	 *
	 * @param manuallySendReadReceipts the manually send read receipts
	 * @return the configuration builder
	 */
	public ConfigurationBuilder setManuallySendReadReceipts(boolean manuallySendReadReceipts) {
		config.getFeatures().setManuallySendReadReceipts(manuallySendReadReceipts);
		return this;
	}
	
	/**
	 * Sets the receive read receipts.
	 *
	 * @param receiveReadReceipts the receive read receipts
	 * @return the configuration builder
	 */
	public ConfigurationBuilder setReceiveReadReceipts(boolean receiveReadReceipts) {
		config.getFeatures().setReceiveReadReceipts(receiveReadReceipts);
		return this;
	}
	
	/**
	 * Sets the receive is typing.
	 *
	 * @param receiveIsTyping the receive is typing
	 * @return the configuration builder
	 */
	public ConfigurationBuilder setReceiveIsTyping(boolean receiveIsTyping) {
		config.getFeatures().setReceiveIsTyping(receiveIsTyping);
		return this;
	}
	
	/**
	 * Sets the receive delivery receipts.
	 *
	 * @param receiveDeliveryReceipts the receive delivery receipts
	 * @return the configuration builder
	 */
	public ConfigurationBuilder setReceiveDeliveryReceipts(boolean receiveDeliveryReceipts) {
		config.getFeatures().setReceiveDeliveryReceipts(receiveDeliveryReceipts);
		return this;
	}
	
	/**
	 * Set the keyboard of the Configuration.
	 *
	 * @param keyboard the keyboard
	 * @return the configuration builder
	 */
	public ConfigurationBuilder setStaticKeyboard(Keyboard keyboard) {
		this.config.setStaticKeyBoard(keyboard);
		return this;
	}
	
	/**
	 * Builds the configuration.
	 *
	 * @return the configuration
	 */
	public Configuration buildConfiguration() {
		NetworkUtils.postJsonConfig(this.config);
		return this.config;
	}
	
}
