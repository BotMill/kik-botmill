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

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * The Class JsonTextAction.
 * 
 * @author Alvin P. Reyes
 */
public class JsonAction {
	
	/** The type. */
	@SerializedName("keyboards")
	private List<JsonKeyboard> keyboards;
	
	/** The input. */
	private String event;
	
	/** The output. */
	private String input;
	
	/** The replies. */
	@SerializedName("replies")
	private List<JsonReply> replies;

	/**
	 * Gets the keyboards.
	 *
	 * @return the keyboards
	 */
	public List<JsonKeyboard> getKeyboards() {
		return keyboards;
	}

	/**
	 * Sets the keyboards.
	 *
	 * @param keyboards the new keyboards
	 */
	public void setKeyboards(List<JsonKeyboard> keyboards) {
		this.keyboards = keyboards;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * Gets the input.
	 *
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * Sets the input.
	 *
	 * @param input the new input
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * Gets the replies.
	 *
	 * @return the replies
	 */
	public List<JsonReply> getReplies() {
		return replies;
	}

	/**
	 * Sets the replies.
	 *
	 * @param replies the new replies
	 */
	public void setReplies(List<JsonReply> replies) {
		this.replies = replies;
	}

	
	
}
