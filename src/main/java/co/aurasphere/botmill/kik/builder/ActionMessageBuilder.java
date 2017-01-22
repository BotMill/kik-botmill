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

import java.util.List;

import co.aurasphere.botmill.kik.model.BaseBuilder;
import co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage;
import co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage;

/**
 * The Class ActionMessageBuilder.
 * 
 * Common Action builder to create isTyping and ReadReceipt response.
 * 
 * @author Alvin P. Reyes
 */
public class ActionMessageBuilder extends BaseBuilder {
	
	/**
	 * Builds the is typing message.
	 *
	 * @return the checks if is typing message
	 */
	public static IsTypingMessage buildIsTypingMessage() {
		return new IsTypingMessage();
	}
	
	/**
	 * Builds the is typing message.
	 *
	 * @param to the to
	 * @return the checks if is typing message
	 */
	public static IsTypingMessage buildIsTypingMessage(String to) {
		return new IsTypingMessage(to);
	}
	
	/**
	 * Builds the read receipt message.
	 *
	 * @param to the to
	 * @param messageIds the message ids
	 * @return the read receipt message
	 */
	public static ReadReceiptMessage buildReadReceiptMessage(String to, List<String> messageIds) {
		return new ReadReceiptMessage(to,messageIds);
	}
}
