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
import co.aurasphere.botmill.kik.model.Attribution;
import co.aurasphere.botmill.kik.model.BaseBuilder;
import co.aurasphere.botmill.kik.model.KeyValuePair;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;

public class LinkMessageBuilder extends BaseBuilder implements Keyboardable<LinkMessageBuilder>,Buildable<LinkMessage> {
	
	private LinkMessage linkMessage;
	private static LinkMessageBuilder instance;
	private KeyboardBuilder<LinkMessageBuilder> keyboardBuilder;
	
	public static LinkMessageBuilder getInstance() {
		if (instance == null) {
			instance = new LinkMessageBuilder();
		}
		return instance;
	}
	
	public LinkMessageBuilder() {
		this.keyboardBuilder = new KeyboardBuilder<LinkMessageBuilder>(this);
		this.linkMessage = new LinkMessage();
		this.linkMessage.setType(MessageType.LINK);
	}
	
	public LinkMessageBuilder setUrl(String url) {
		this.linkMessage.setUrl(url);
		return this;
	}
	
	public LinkMessageBuilder setTitle(String title) {
		this.linkMessage.setTitle(title);
		return this;
	}
	
	public LinkMessageBuilder setText(String text) {
		this.linkMessage.setText(text);
		return this;
	}
	
	public LinkMessageBuilder setPicUrl(String picUrl) {
		this.linkMessage.setPicUrl(picUrl);
		return this;
	}
	
	public LinkMessageBuilder setNoForward(boolean noForward) {
		this.linkMessage.setNoForward(noForward);
		return this;
	}
	
	public LinkMessageBuilder setKikJsData(KeyValuePair kikJsData) {
		this.linkMessage.setKikJsData(kikJsData);
		return this;
	}
	
	public LinkMessageBuilder setAttribution(Attribution attribution) {
		this.linkMessage.setAttribution(attribution);
		return this;
	}
	
	@Override
	public KeyboardBuilder<LinkMessageBuilder> addKeyboard() {
		return this.keyboardBuilder;
	}
	
	@Override
	public LinkMessageBuilder endKeyboard() {
		return (LinkMessageBuilder)this.keyboardBuilder.getParentBuilder();
	}
	
	@Override
	public LinkMessage build() {
		this.linkMessage.setKeyboard(this.keyboardBuilder.buildKeyboard());
		return this.linkMessage;
	}
}
