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

import java.util.List;

import co.aurasphere.botmill.kik.model.Message;

/**
 * The Class IncomingMessage.
 * 
 * @author Alvin P. Reyes
 */
public abstract class IncomingMessage extends Message {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The from. */
	private String from;
	
	/** The participants. */
	private List<String> participants;
	
	/** The body. */
	private String body;
	
	/** The timestamp. */
	private String timestamp;
	
	/** The read receipt requested. */
	private String readReceiptRequested;
	
	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * Gets the participants.
	 *
	 * @return the participants
	 */
	public List<String> getParticipants() {
		return participants;
	}
	
	/**
	 * Sets the participants.
	 *
	 * @param participants the new participants
	 */
	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	
	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the read receipt requested.
	 *
	 * @return the read receipt requested
	 */
	public String getReadReceiptRequested() {
		return readReceiptRequested;
	}
	
	/**
	 * Sets the read receipt requested.
	 *
	 * @param readReceiptRequested the new read receipt requested
	 */
	public void setReadReceiptRequested(String readReceiptRequested) {
		this.readReceiptRequested = readReceiptRequested;
	}
}
