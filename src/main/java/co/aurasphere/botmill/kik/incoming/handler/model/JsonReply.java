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
package co.aurasphere.botmill.kik.incoming.handler.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;
import co.aurasphere.botmill.kik.outgoing.model.VideoMessage;

/**
 * The Class JsonReply.
 */
public class JsonReply {
	
	
	/** The type. */
	@SerializedName("type")
	private String type;


	/** The text. */
	@SerializedName("text")
	private TextMessage text;
	
	/** The picture. */
	@SerializedName("picture")
	private PictureMessage picture;
	
	/** The link. */
	@SerializedName("link")
	private LinkMessage link;
	
	/** The video. */
	@SerializedName("video")
	private VideoMessage video;
	
	/** The keyboard. */
	@SerializedName("keyboards")
	private JsonKeyboard keyboard;
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public TextMessage getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(TextMessage text) {
		this.text = text;
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public PictureMessage getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(PictureMessage picture) {
		this.picture = picture;
	}

	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public LinkMessage getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 *
	 * @param link the new link
	 */
	public void setLink(LinkMessage link) {
		this.link = link;
	}

	/**
	 * Gets the video.
	 *
	 * @return the video
	 */
	public VideoMessage getVideo() {
		return video;
	}

	/**
	 * Sets the video.
	 *
	 * @param video the new video
	 */
	public void setVideo(VideoMessage video) {
		this.video = video;
	}

	/**
	 * Gets the keyboard.
	 *
	 * @return the keyboard
	 */
	public JsonKeyboard getKeyboard() {
		return keyboard;
	}

	/**
	 * Sets the keyboard.
	 *
	 * @param keyboard the new keyboard
	 */
	public void setKeyboard(JsonKeyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	
}
