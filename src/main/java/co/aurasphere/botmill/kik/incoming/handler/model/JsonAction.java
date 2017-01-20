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
	
	@SerializedName("replies")
	private List<JsonReply> replies;

	public List<JsonKeyboard> getKeyboards() {
		return keyboards;
	}

	public void setKeyboards(List<JsonKeyboard> keyboards) {
		this.keyboards = keyboards;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public List<JsonReply> getReplies() {
		return replies;
	}

	public void setReplies(List<JsonReply> replies) {
		this.replies = replies;
	}

	
	
}
