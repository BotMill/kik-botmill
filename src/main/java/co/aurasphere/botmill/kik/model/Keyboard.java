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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Keyboard.
 * 
 * @author Alvin P. Reyes
 */
public class Keyboard implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The type. */
	private KeyboardType type;
	
	/** The responses. */
	private List<Response> responses;
	
	/** The to. */
	private String to;
	
	/** The hidden. */
	private Boolean hidden;
	
	
	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Gets the hidden.
	 *
	 * @return the hidden
	 */
	public Boolean getHidden() {
		return hidden;
	}

	/**
	 * Sets the hidden.
	 *
	 * @param hidden the new hidden
	 */
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Instantiates a new keyboard.
	 */
	public Keyboard() {
		this.responses = new ArrayList<Response>();
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public KeyboardType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(KeyboardType type) {
		this.type = type;
	}
	
	/**
	 * Gets the responses.
	 *
	 * @return the responses
	 */
	public List<Response> getResponses() {
		return responses;
	}
	
	/**
	 * Sets the responses.
	 *
	 * @param responses the new responses
	 */
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	

}
