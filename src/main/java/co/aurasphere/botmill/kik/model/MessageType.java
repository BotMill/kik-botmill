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

import com.google.gson.annotations.SerializedName;

/**
 * The Enum MessageType.
 */
public enum MessageType {

	/** The text. */
	@SerializedName("text")
	TEXT,
	/** The link. */
	@SerializedName("link")
	LINK,
	/** The picture. */
	@SerializedName("picture")
	PICTURE,
	/** The video. */
	@SerializedName("video")
	VIDEO,
	/** The is typing. */
	@SerializedName("is-typing")
	IS_TYPING,
	/** The read receipt. */
	@SerializedName("read-receipt")
	READ_RECEIPT,
	/** The scan data. */
	@SerializedName("scan-data")
	SCAN_DATA,
	/** The sticker. */
	@SerializedName("sticker")
	STICKER,
	/** The friend picker. */
	@SerializedName("friend-picker")
	FRIEND_PICKER,
	
	/** The start chatting. */
	@SerializedName("start-chatting")
	START_CHATTING,
	
	/** The delivery receipt. */
	@SerializedName("delivery-receipt")
	DELIVERY_RECEIPT
}
