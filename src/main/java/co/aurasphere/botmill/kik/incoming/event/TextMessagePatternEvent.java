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
package co.aurasphere.botmill.kik.incoming.event;

import java.util.regex.Pattern;
import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.model.Event;

/**
 * The Class TextMessagePatternEvent.
 */
public class TextMessagePatternEvent implements Event {

	/** The keyword pattern. */
	private String keywordPattern;

	/**
	 * Instantiates a new text message pattern event.
	 */
	public TextMessagePatternEvent() {}

	/**
	 * Sets the pattern.
	 *
	 * @param text the text
	 * @return the text message pattern event
	 */
	public TextMessagePatternEvent setPattern(String text) {
		this.keywordPattern = text;
		return this;
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Event#verifyEvent(co.aurasphere.botmill.kik.incoming.model.IncomingMessage)
	 */
	@Override
	public boolean verifyEvent(IncomingMessage incomingMessage) {
		if (incomingMessage instanceof TextMessage) {
			if (this.keywordPattern == null) {
				return false;
			}
			Pattern pattern = Pattern.compile(keywordPattern);
			return pattern.matcher(incomingMessage.getBody()).matches();
		}
		return false;
	}
}
