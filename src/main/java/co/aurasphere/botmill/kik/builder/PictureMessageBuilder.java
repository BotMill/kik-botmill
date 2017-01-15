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
import co.aurasphere.botmill.kik.model.MediaAttribution;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;

/**
 * The Class PictureMessageBuilder.
 */
public class PictureMessageBuilder extends BaseBuilder
		implements Keyboardable<PictureMessageBuilder>, Buildable<PictureMessage> {
	
	/** The picture message. */
	private static PictureMessage pictureMessage;
	
	/** The instance. */
	private static PictureMessageBuilder instance;
	
	/** The keyboard builder. */
	private static KeyboardBuilder<PictureMessageBuilder> keyboardBuilder;

	/**
	 * Gets the single instance of PictureMessageBuilder.
	 *
	 * @return single instance of PictureMessageBuilder
	 */
	public static PictureMessageBuilder getInstance() {
		if (instance == null) {
			instance = new PictureMessageBuilder();
		}
		pictureMessage = new PictureMessage();
		pictureMessage.setType(MessageType.PICTURE);
		return instance;
	}

	/**
	 * Instantiates a new picture message builder.
	 */
	public PictureMessageBuilder() {
		pictureMessage = new PictureMessage();
		pictureMessage.setType(MessageType.PICTURE);
	}

	/**
	 * Sets the to.
	 *
	 * @param to the to
	 * @return the picture message builder
	 */
	public PictureMessageBuilder setTo(String to) {
		pictureMessage.setTo(to);
		return this;
	}

	/**
	 * Sets the pic url.
	 *
	 * @param picUrl the pic url
	 * @return the picture message builder
	 */
	public PictureMessageBuilder setPicUrl(String picUrl) {
		pictureMessage.setPicUrl(picUrl);
		return this;
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Keyboardable#addKeyboard()
	 */
	@Override
	public KeyboardBuilder<PictureMessageBuilder> addKeyboard() {
		keyboardBuilder = new KeyboardBuilder<PictureMessageBuilder>(this);
		pictureMessage.addKeyboard(keyboardBuilder.buildKeyboard());
		return keyboardBuilder;
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Keyboardable#endKeyboard()
	 */
	@Override
	public PictureMessageBuilder endKeyboard() {
		return (PictureMessageBuilder) keyboardBuilder.getParentBuilder();
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Buildable#build()
	 */
	@Override
	public PictureMessage build() {
		pictureMessage.setAttribution(MediaAttribution.GALLERY);
		return pictureMessage;
	}
}
