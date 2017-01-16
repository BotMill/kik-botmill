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
package co.aurasphere.botmill.kik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * The Class MessagePostback.
 */
public class MessagePostback implements Serializable{
	
	/** The messages. */
	@SerializedName("messages")
	private List<Message> messages;
	
	/**
	 * Instantiates a new message postback.
	 */
	public MessagePostback() {
		messages = new ArrayList<Message>();
	}
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * Sets the messages.
	 *
	 * @param messages the new messages
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	/**
	 * Adds the message.
	 *
	 * @param message the message
	 */
	public void addMessage(Message message) {
		this.messages.add(message);
	}
}
