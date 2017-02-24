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
package co.aurasphere.botmill.kik.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.SerializedName;


/**
 * The Class Message.
 * 
 * @author Alvin P. Reyes
 */
public class Message implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The chat id. */
	@NotEmpty
	private String chatId;
	
	/** The id. */
	private String id;
	
	/** The type. */
	@NotEmpty
	@SerializedName("type")
	private MessageType type;
	
	/** The mention. */
	private String mention;
	
	/** The metadata. */
	private String metadata;
	
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public MessageType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(MessageType type) {
		this.type = type;
	}
	
	/**
	 * Gets the mention.
	 *
	 * @return the mention
	 */
	public String getMention() {
		return mention;
	}
	
	/**
	 * Sets the mention.
	 *
	 * @param mention the new mention
	 */
	public void setMention(String mention) {
		this.mention = mention;
	}

	/**
	 * Gets the metadata.
	 *
	 * @return the metadata
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * Sets the metadata.
	 *
	 * @param metadata the new metadata
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

}
