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
package co.aurasphere.botmill.kik.outgoing.model;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;

/**
 * The Class TextMessage.
 */
public class TextMessage extends OutgoingMessage {
	
	/** The type time. */
	private String typeTime;
	
	/** The keyboard. */
	@SerializedName("keyboards")
	private Keyboard keyboard;

	/**
	 * Gets the type time.
	 *
	 * @return the type time
	 */
	public String getTypeTime() {
		return typeTime;
	}

	/**
	 * Sets the type time.
	 *
	 * @param typeTime the new type time
	 */
	public void setTypeTime(String typeTime) {
		this.typeTime = typeTime;
	}

	/**
	 * Gets the keyboard.
	 *
	 * @return the keyboard
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}

	/**
	 * Sets the keyboard.
	 *
	 * @param keyboard the new keyboard
	 */
	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
}
