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
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class MessageEnvelope.
 */
public class MessageEnvelope implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The chat id. */
	@NotNull
	@NotEmpty
	private String chatId;
	
	/** The participants. */
	private List<String> participants;
	
	/** The incoming message. */
	private Message incomingMessage;
	
	/**
	 * Gets the chat id.
	 *
	 * @return the chat id
	 */
	public String getChatId() {
		return chatId;
	}
	
	/**
	 * Sets the chat id.
	 *
	 * @param chatId the new chat id
	 */
	public void setChatId(String chatId) {
		this.chatId = chatId;
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
	 * Gets the incoming message.
	 *
	 * @return the incoming message
	 */
	public Message getIncomingMessage() {
		return incomingMessage;
	}
	
	/**
	 * Sets the incoming message.
	 *
	 * @param incomingMessage the new incoming message
	 */
	public void setIncomingMessage(Message incomingMessage) {
		this.incomingMessage = incomingMessage;
	}
	
	
	
	
}
