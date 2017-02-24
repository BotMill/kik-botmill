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
package co.aurasphere.botmill.kik.incoming.model;

/**
 * The Class StickerMessage.
 * 
 * @author Alvin P. Reyes
 */
public class StickerMessage extends IncomingMessage {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sticker pack id. */
    private String stickerPackId;
	
	/** The sticker url. */
	private String stickerUrl;
	
	/**
	 * Gets the sticker pack id.
	 *
	 * @return the sticker pack id
	 */
	public String getStickerPackId() {
		return stickerPackId;
	}
	
	/**
	 * Sets the sticker pack id.
	 *
	 * @param stickerPackId the new sticker pack id
	 */
	public void setStickerPackId(String stickerPackId) {
		this.stickerPackId = stickerPackId;
	}
	
	/**
	 * Gets the sticker url.
	 *
	 * @return the sticker url
	 */
	public String getStickerUrl() {
		return stickerUrl;
	}
	
	/**
	 * Sets the sticker url.
	 *
	 * @param stickerUrl the new sticker url
	 */
	public void setStickerUrl(String stickerUrl) {
		this.stickerUrl = stickerUrl;
	}
	
}
