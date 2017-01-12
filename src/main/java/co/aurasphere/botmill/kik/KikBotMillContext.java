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
package co.aurasphere.botmill.kik;

import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.configuration.Authentication;
import co.aurasphere.botmill.kik.model.ActionFrame;

/**
 * The Class KikBotMillContext.
 */
public class KikBotMillContext {
	
	/** The instance. */
	private static KikBotMillContext instance;
	
	/** The authentication. */
	private Authentication authentication;
	
	/** The webhook url. */
	private String webhookUrl;
	
	/** The bots. */
	private List<KikBotMillEntry> entryPoints;
	
	/**
	 * Instantiates a new kik bot mill context.
	 */
	public KikBotMillContext() {
		this.entryPoints = new ArrayList<KikBotMillEntry>();
	}

	/**
	 * Gets the single instance of KikBotMillContext.
	 *
	 * @return single instance of KikBotMillContext
	 */
	public static KikBotMillContext getInstance() {
		if (instance == null) {
			instance = new KikBotMillContext();
		}
		return instance;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser(){
		return this.authentication.getUser();
	}
	
	/**
	 * Gets the api key.
	 *
	 * @return the api key
	 */
	public String getApiKey() {
		return this.authentication.getApiKey();
	}
	
	/**
	 * Setup.
	 *
	 * @param username the username
	 * @param apiKey the api key
	 */
	public void setup(String username, String apiKey) {
		this.authentication = new Authentication();
		this.authentication.setUser(username);
		this.authentication.setApiKey(apiKey);
	}
	
	/**
	 * Register kik bot.
	 *
	 * @param kikBotMillEntry the kikBotMillEntry
	 */
	public void registerEntryPoint(KikBotMillEntry kikBotMillEntry) {
		this.entryPoints.add(kikBotMillEntry);
	}
	
	/**
	 * Sets the web hook url.
	 *
	 * @param url the new web hook url
	 */
	public void setWebHookUrl(String url) {
		this.webhookUrl = url;
	}
	
	/**
	 * Gets the web hook url.
	 *
	 * @return the web hook url
	 */
	public String getWebHookUrl() {
		return this.webhookUrl;
	}
}