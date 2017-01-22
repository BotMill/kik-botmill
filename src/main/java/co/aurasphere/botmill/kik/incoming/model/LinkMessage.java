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
package co.aurasphere.botmill.kik.incoming.model;


import co.aurasphere.botmill.kik.model.Attribution;
import co.aurasphere.botmill.kik.model.KikJsData;

/**
 * The Class LinkMessage.
 * 
 * @author Alvin P. Reyes
 */
public class LinkMessage extends IncomingMessage implements Comparable<LinkMessage> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The url. */
	private String url;
	
	/** The no forward. */
	private String noForward;
	
	/** The kik js data. */
	private KikJsData kikJsData;
	
	/** The attribution. */
	private Attribution attribution;
	
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
	 * Gets the no forward.
	 *
	 * @return the no forward
	 */
	public String getNoForward() {
		return noForward;
	}
	
	/**
	 * Sets the no forward.
	 *
	 * @param noForward the new no forward
	 */
	public void setNoForward(String noForward) {
		this.noForward = noForward;
	}
	
	/**
	 * Gets the kik js data.
	 *
	 * @return the kik js data
	 */
	public KikJsData getKikJsData() {
		return kikJsData;
	}
	
	/**
	 * Sets the kik js data.
	 *
	 * @param kikJsData the new kik js data
	 */
	public void setKikJsData(KikJsData kikJsData) {
		this.kikJsData = kikJsData;
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
	
	@Override
	public int compareTo(LinkMessage o) {
		if(this.getUrl().equals(o.getUrl())) {
			return 0;
		}
		return -1;
	}
}
