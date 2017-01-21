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

import co.aurasphere.botmill.kik.model.BaseBuilder;
import co.aurasphere.botmill.kik.model.Keyboard;
import co.aurasphere.botmill.kik.model.KeyboardType;
import co.aurasphere.botmill.kik.model.Response;

/**
 * The Class KeyboardBuilder.
 * 
 * @author Alvin P. Reyes
 *
 */
public class KeyboardBuilder extends BaseBuilder {
	
	/** The keyboard. */
	private static Keyboard keyboard;
	
	/** The instance. */
	private static KeyboardBuilder instance;
	
	/**
	 * Gets the single instance of KeyboardBuilder.
	 *
	 * @return single instance of KeyboardBuilder
	 */
	public static KeyboardBuilder getInstance() {
		if (instance == null) {
			instance = new KeyboardBuilder();
		}
		keyboard = new Keyboard();
		return instance;
	}
	
	/**
	 * Instantiates a new keyboard builder.
	 */
	public KeyboardBuilder() {
		 keyboard = new Keyboard();
	}
	
	/**
	 * Sets the to.
	 *
	 * @param to the to
	 * @return the keyboard builder
	 */
	public KeyboardBuilder setTo(String to) {
		keyboard.setTo(to);
		return this;
	}
	
	/**
	 * Sets the hidden.
	 *
	 * @param hidden the hidden
	 * @return the keyboard builder
	 */
	public KeyboardBuilder setHidden(Boolean hidden) {
		keyboard.setHidden(hidden);
		return this;
	}
	/**
	 * Sets the type.
	 *
	 * @param type the type
	 * @return the keyboard builder
	 */
	public KeyboardBuilder setType(KeyboardType type) {
		keyboard.setType(type);
		return this;
	}
	
	/**
	 * Adds the response.
	 *
	 * @param e the e
	 * @return the keyboard builder
	 */
	public KeyboardBuilder addResponse(Response e) {
		keyboard.getResponses().add(e);
		return this;
	}

	/**
	 * Builds the keyboard.
	 *
	 * @return the keyboard
	 */
	public Keyboard buildKeyboard() {
		return keyboard;
	}
}
