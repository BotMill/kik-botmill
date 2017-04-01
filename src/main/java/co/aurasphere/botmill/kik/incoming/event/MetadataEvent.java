/*
 * 
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
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
package co.aurasphere.botmill.kik.incoming.event;
import java.util.HashMap;
import java.util.Map;

import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.model.KikBotMillEvent;
import co.aurasphere.botmill.kik.util.json.JsonUtils;

/**
 * The Class MetadataEvent.
 * 
 * @author Alvin P. Reyes
 */
public class MetadataEvent implements KikBotMillEvent {

	/** The key. */
	private String key;
	
	/** The value. */
	private String value;
	
	/** The metadata map. */
	private Map<String,String> metadataMap = new HashMap<String,String>();
	
	/**
	 * Instantiates a new text message event.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public MetadataEvent(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public MetadataEvent(String key) {}

	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.model.Event#verifyEvent(co.aurasphere.botmill.kik.incoming.model.IncomingMessage)
	 */
	@Override
	public boolean verifyEvent(IncomingMessage message) {
		if(message.getMetadata() != null) {
			metadataMap = (Map<String,String>)JsonUtils.fromJson(message.getMetadata(), metadataMap.getClass());
			if(metadataMap.get(key) != null) {
				return true;
			}
		}
		return false;
	}
}
