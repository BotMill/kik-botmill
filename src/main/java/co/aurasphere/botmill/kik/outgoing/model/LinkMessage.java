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
package co.aurasphere.botmill.kik.outgoing.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;
import co.aurasphere.botmill.kik.model.Attribution;
import co.aurasphere.botmill.kik.model.KeyValuePair;

/**
 * The Class LinkMessage.
 */
public class LinkMessage extends OutgoingMessage {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The url. */
	private String url;
	
	/** The title. */
	private String title;
	
	/** The text. */
	private String text;
	
	/** The no forward. */
	private boolean noForward;
	
	/** The kik js data. */
	@SerializedName("kikJsData")
	private KeyValuePair kikJsData = new KeyValuePair();
	
	/** The attribution. */
	private Attribution attribution;
	
	/** The pic url. */
	private String picUrl;
	
	/** The keyboard. */
	@SerializedName("keyboards")
	private List<Keyboard> keyboards = new ArrayList<Keyboard>();

	/**
	 * Gets the keyboards.
	 *
	 * @return the keyboards
	 */
	public List<Keyboard> getKeyboards() {
		return keyboards;
	}

	/**
	 * Sets the keyboards.
	 *
	 * @param keyboards the new keyboards
	 */
	public void setKeyboards(List<Keyboard> keyboards) {
		this.keyboards = keyboards;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the no forward.
	 *
	 * @return the no forward
	 */
	public boolean getNoForward() {
		return noForward;
	}

	/**
	 * Sets the no forward.
	 *
	 * @param noForward the new no forward
	 */
	public void setNoForward(boolean noForward) {
		this.noForward = noForward;
	}

	/**
	 * Gets the kik js data.
	 *
	 * @return the kik js data
	 */
	public KeyValuePair getKikJsData() {
		return kikJsData;
	}

	/**
	 * Sets the kik js data.
	 *
	 * @param keyValuePair the new kik js data
	 */
	public void setKikJsData(KeyValuePair keyValuePair) {
		this.kikJsData.setKey(keyValuePair.getKey());
		this.kikJsData.setValue(keyValuePair.getValue());
	}

	/**
	 * Gets the attribution.
	 *
	 * @return the attribution
	 */
	public Attribution getAttribution() {
		return attribution;
	}

	/**
	 * Sets the attribution.
	 *
	 * @param attribution the new attribution
	 */
	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}

	/**
	 * Gets the pic url.
	 *
	 * @return the pic url
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * Sets the pic url.
	 *
	 * @param picUrl the new pic url
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * Sets the keyboard.
	 *
	 * @param keyboard the new keyboard
	 */
	public void addKeyboard(Keyboard keyboard) {
		this.keyboards.add(keyboard);
	}
	
	
	
	
}
