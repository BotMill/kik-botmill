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

public class TextMessageBuilder extends BaseBuilder
		implements Keyboardable<TextMessageBuilder>, Buildable<TextMessage> {
	
	private TextMessage textMessage;
	private static TextMessageBuilder instance;
	private KeyboardBuilder<TextMessageBuilder> keyboardBuilder;
	
	public static TextMessageBuilder getInstance() {
		if (instance == null) {
			instance = new TextMessageBuilder();
		}
		return instance;
	}
	
	public TextMessageBuilder() {
		this.keyboardBuilder = new KeyboardBuilder<TextMessageBuilder>(this);
		this.textMessage = new TextMessage();
		this.textMessage.setType(MessageType.TEXT);
	}
	
	public TextMessageBuilder setTo(String to) {
		this.textMessage.setTo(to);
		return this;
	}
	
	public TextMessageBuilder setBody(String body) {
		this.textMessage.setBody(body);
		return this;
	}
	
	@Override
	public KeyboardBuilder<TextMessageBuilder> addKeyboard() {
		return this.keyboardBuilder;
	}
	
	@Override
	public TextMessageBuilder endKeyboard() {
		return (TextMessageBuilder)this.keyboardBuilder.getParentBuilder();
	}
	
	@Override
	public TextMessage build() {
		this.textMessage.setKeyboard(this.keyboardBuilder.buildKeyboard());
		return this.textMessage;
	}
	
}
