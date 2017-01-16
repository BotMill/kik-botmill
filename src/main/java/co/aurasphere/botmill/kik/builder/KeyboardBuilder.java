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

import co.aurasphere.botmill.kik.configuration.Keyboard;
import co.aurasphere.botmill.kik.configuration.KeyboardType;
import co.aurasphere.botmill.kik.configuration.Response;
import co.aurasphere.botmill.kik.model.BaseBuilder;

/**
 * The Class KeyboardBuilder.
 * 
 * @author Alvin P. Reyes
 *
 * @param <T> the generic type
 */
public class KeyboardBuilder<T> extends BaseBuilder {
	
	/** The keyboard. */
	private Keyboard keyboard;
	
	/** The parent builder. */
	private T parentBuilder;
	
	/**
	 * Instantiates a new keyboard builder.
	 */
	public KeyboardBuilder() {
		 this.keyboard = new Keyboard();
	}
	
	/**
	 * Instantiates a new keyboard builder.
	 *
	 * @param t the t
	 */
	public KeyboardBuilder(T t) {
		 this.keyboard = new Keyboard();
		 this.parentBuilder = t;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the type
	 * @return the keyboard builder
	 */
	public KeyboardBuilder<T> setType(KeyboardType type) {
		this.keyboard.setType(type);
		return this;
	}
	
	/**
	 * Adds the response.
	 *
	 * @param e the e
	 * @return the keyboard builder
	 */
	public KeyboardBuilder<T> addResponse(Response e) {
		this.keyboard.getResponses().add(e);
		return this;
	}
	
	/**
	 * End keyboard.
	 *
	 * @return the t
	 */
	public T endKeyboard() {
		return this.parentBuilder;
	}

	/**
	 * Builds the keyboard.
	 *
	 * @return the keyboard
	 */
	public Keyboard buildKeyboard() {
		return this.keyboard;
	}
}
