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
package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;


/**
 * The Class Configuration.
 */
public class Configuration implements Serializable  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The webhook. */
	private String webhook;
	
	/** The features. */
	private Features features;
	
	/** The keyboard. */
	@SerializedName("staticKeyboard")
	private Keyboard keyboard;
	
	/**
	 * Instantiates a new configuration.
	 */
	public Configuration() {
		this.features = new Features();
	}
	
	/**
	 * Gets the static key board.
	 *
	 * @return the static key board
	 */
	public Keyboard getStaticKeyBoard() {
		return keyboard;
	}
	
	/**
	 * Sets the static key board.
	 *
	 * @param keyboard the new static key board
	 */
	public void setStaticKeyBoard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	/**
	 * Gets the webhook.
	 *
	 * @return the webhook
	 */
	public String getWebhook() {
		return webhook;
	}
	
	/**
	 * Sets the webhook.
	 *
	 * @param webhook the new webhook
	 */
	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}
	
	/**
	 * Gets the features.
	 *
	 * @return the features
	 */
	public Features getFeatures() {
		return features;
	}
	
	/**
	 * Sets the features.
	 *
	 * @param features the new features
	 */
	public void setFeatures(Features features) {
		this.features = features;
	}	
}
