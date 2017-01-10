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

/**
 * The Class KikBot.
 * 
 * @author Alvin Reyes
 */
public class KikBot {

	/**
	 * On text received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onTextReceived(Message1 msg) {
	}

	/**
	 * On image received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onImageReceived(Message1 msg) {
	}

	/**
	 * On video received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onVideoReceived(Message1 msg) {
	}

	/**
	 * On url received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onUrlReceived(Message1 msg) {
	}

	/**
	 * On start chatting received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onStartChattingReceived(Message1 msg) {
	}

	/**
	 * On user typing received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onUserTypingReceived(Message1 msg) {
	}

	/**
	 * On friend picked received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onFriendPickedReceived(Message1 msg) {
	}

	/**
	 * On message.
	 *
	 * @param message
	 *            the message
	 */
	public void onMessage(Message1 message) {
		switch (message.getType()) {
		case Message1.Type.TEXT:
			onTextReceived(message);
			break;

		case Message1.Type.IMAGE:
			onImageReceived(message);
			break;

		case Message1.Type.VIDEO:
			onVideoReceived(message);
			break;

		case Message1.Type.URL:
			onUrlReceived(message);
			break;

		case Message1.Type.START_CHATTING:
			onStartChattingReceived(message);
			break;

		case Message1.Type.IS_TYPING:
			onUserTypingReceived(message);
		case Message1.Type.FRIEND_PICKER:
			onFriendPickedReceived(message);
			break;

		default:
			throw new RuntimeException("The message type " + message.getType() + " was not handled.");
		}
	}

}
