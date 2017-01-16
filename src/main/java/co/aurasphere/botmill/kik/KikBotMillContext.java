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
import co.aurasphere.botmill.kik.incoming.event.LinkMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.PictureMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessagePatternEvent;
import co.aurasphere.botmill.kik.incoming.event.VideoMessageEvent;
import co.aurasphere.botmill.kik.model.Domain;
import co.aurasphere.botmill.kik.model.Frame;

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
	
	/** The domains. */
	private List<Domain> domains;
	
	/** The action frames. */
	private List<Frame> actionFrames;
	
	/** The text message action frames. */
	//buckets
	private List<Frame> textMessageActionFrames;
	
	/** The media message action frames. */
	private List<Frame> mediaMessageActionFrames;
	
	/** The link message action frames. */
	private List<Frame> linkMessageActionFrames;
	
	/** The broadcast action frames. */
	private List<Frame> broadcastActionFrames;
	
	

	/**
	 * Gets the domains.
	 *
	 * @return the domains
	 */
	public List<Domain> getDomains() {
		return domains;
	}

	/**
	 * Gets the action frames.
	 *
	 * @return the action frames
	 */
	public List<Frame> getActionFrames() {
		return actionFrames;
	}

	/**
	 * Gets the text message action frames.
	 *
	 * @return the text message action frames
	 */
	public List<Frame> getTextMessageActionFrames() {
		return textMessageActionFrames;
	}

	/**
	 * Gets the media message action frames.
	 *
	 * @return the media message action frames
	 */
	public List<Frame> getMediaMessageActionFrames() {
		return mediaMessageActionFrames;
	}

	/**
	 * Gets the link message action frames.
	 *
	 * @return the link message action frames
	 */
	public List<Frame> getLinkMessageActionFrames() {
		return linkMessageActionFrames;
	}
	
	/**
	 * Gets the broadcast message action frames.
	 *
	 * @return the broadcast message action frames
	 */
	public List<Frame> getBroadcastMessageActionFrames() {
		return broadcastActionFrames;
	}

	/**
	 * Instantiates a new kik bot mill context.
	 */
	public KikBotMillContext() {
		this.entryPoints = new ArrayList<KikBotMillEntry>();
		this.domains = new ArrayList<Domain>();
		this.actionFrames = new ArrayList<Frame>();
		this.textMessageActionFrames = new ArrayList<Frame>();
		this.mediaMessageActionFrames = new ArrayList<Frame>();
		this.linkMessageActionFrames = new ArrayList<Frame>();
		this.broadcastActionFrames = new ArrayList<Frame>();
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
	
	/**
	 * Register domain.
	 *
	 * @param domain the domain
	 */
	public void registerDomain(Domain domain) {
		this.domains.add(domain);
	}
	
	/**
	 * Adds the action frame to context.
	 *
	 * @param actionFrame the action frame
	 */
	public void addActionFrameToContext(Frame actionFrame) {
		if((actionFrame.getEvent() instanceof TextMessageEvent) || (actionFrame.getEvent() instanceof TextMessagePatternEvent)){
			this.textMessageActionFrames.add(actionFrame);
		}else if((actionFrame.getEvent() instanceof LinkMessageEvent)) {
			this.linkMessageActionFrames.add(actionFrame);
		}else if((actionFrame.getEvent() instanceof PictureMessageEvent) 
				||(actionFrame.getEvent() instanceof VideoMessageEvent)) {
			this.mediaMessageActionFrames.add(actionFrame);
		}else {
			this.actionFrames.add(actionFrame);
		}
	}
	
	/**
	 * Adds the action frame to broadcast.
	 *
	 * @param actionFrame the action frame
	 */
	public void addActionFrameToBroadcast(Frame actionFrame) {
		this.broadcastActionFrames.add(actionFrame);
	}
}