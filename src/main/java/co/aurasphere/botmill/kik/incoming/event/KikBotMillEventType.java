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
package co.aurasphere.botmill.kik.incoming.event;

/**
 * The Enum EventType.
 */
public enum KikBotMillEventType {
	
 /** The any. */
 ANY, 
 /** The delivery receipt. */
 DELIVERY_RECEIPT, 
 /** The friend picker. */
 FRIEND_PICKER, 
 /** The is typing. */
 IS_TYPING, 
 /** The link. */
 LINK, 
 /** The mention. */
 MENTION, 
 /** The picture. */
 PICTURE, 
 /** The scan data. */
 SCAN_DATA, 
 /** The start chatting. */
 START_CHATTING, 
 /** The sticker. */
 STICKER, 
 /** The text message. */
 TEXT_MESSAGE, 
 /** The text pattern. */
 TEXT_PATTERN, 
 /** The video. */
 VIDEO,
 /** The Read Receipt Event **/
 READ_RECEIPT
}
