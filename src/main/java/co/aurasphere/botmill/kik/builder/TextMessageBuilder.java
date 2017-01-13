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
package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.intf.Buildable;
import co.aurasphere.botmill.kik.intf.Keyboardable;
import co.aurasphere.botmill.kik.model.BaseBuilder;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;
import co.aurasphere.botmill.kik.reply.TextMessageReply;

/**
 * The Class TextMessageBuilder.
 */
public class TextMessageBuilder extends BaseBuilder
		implements Keyboardable<TextMessageBuilder>, Buildable<TextMessage> {
	
	/** The text message. */
	private TextMessage textMessage;
	
	private TextMessageReply textMessageReply;
	
	/** The instance. */
	private static TextMessageBuilder instance;
	
	/** The keyboard builder. */
	private KeyboardBuilder<TextMessageBuilder> keyboardBuilder;
	
	/**
	 * Gets the single instance of TextMessageBuilder.
	 *
	 * @return single instance of TextMessageBuilder
	 */
	public static TextMessageBuilder getInstance() {
		if (instance == null) {
			instance = new TextMessageBuilder();
		}
		return instance;
	}
	
	/**
	 * Instantiates a new text message builder.
	 */
	public TextMessageBuilder() {
		this.keyboardBuilder = new KeyboardBuilder<TextMessageBuilder>(this);
		this.textMessage = new TextMessage();
		this.textMessage.setType(MessageType.TEXT);
	}
	
	/**
	 * Sets the to.
	 *
	 * @param to the to
	 * @return the text message builder
	 */
	public TextMessageBuilder setTo(String to) {
		this.textMessage.setTo(to);
		return this;
	}
	
	/**
	 * Sets the body.
	 *
	 * @param body the body
	 * @return the text message builder
	 */
	public TextMessageBuilder setBody(String body) {
		this.textMessage.setBody(body);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Keyboardable#addKeyboard()
	 */
	@Override
	public KeyboardBuilder<TextMessageBuilder> addKeyboard() {
		return this.keyboardBuilder;
	}
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Keyboardable#endKeyboard()
	 */
	@Override
	public TextMessageBuilder endKeyboard() {
		return (TextMessageBuilder)this.keyboardBuilder.getParentBuilder();
	}
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Buildable#build()
	 */
	@Override
	public TextMessage build() {
		this.textMessage.setKeyboard(this.keyboardBuilder.buildKeyboard());
		return this.textMessage;
	}
	
	
	public TextMessageReply buildReply() {
		return this.textMessageReply;
	}
}
