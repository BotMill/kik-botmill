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

import java.util.ArrayList;

/**
 * The Class FriendPicker.
 * 
 * @author Alvin Reyes
 */
public class FriendPicker {

	/** The body. */
	private String body = "";

	/** The max. */
	private int min, max;

	/** The preselected. */
	private ArrayList<String> preselected = new ArrayList<String>();

	/**
	 * Instantiates a new friend picker.
	 *
	 * @param body
	 *            the body
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 */
	public FriendPicker(String body, int min, int max) {
		this.body = body;
		this.min = min;
		this.max = max;
	}

	/**
	 * Instantiates a new friend picker.
	 *
	 * @param body
	 *            the body
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @param preselected
	 *            the preselected
	 */
	public FriendPicker(String body, int min, int max, ArrayList<String> preselected) {
		this.body = body;
		this.min = min;
		this.max = max;
		this.preselected = preselected;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return Message1.Type.FRIEND_PICKER;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Gets the min.
	 *
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Gets the max.
	 *
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Gets the preselected.
	 *
	 * @return the preselected
	 */
	public ArrayList<String> getPreselected() {
		return preselected;
	}

	/**
	 * Sets the body.
	 *
	 * @param body
	 *            the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Sets the min.
	 *
	 * @param min
	 *            the new min
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Sets the max.
	 *
	 * @param max
	 *            the new max
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * Sets the preselected.
	 *
	 * @param preselected
	 *            the new preselected
	 */
	public void setPreselected(ArrayList<String> preselected) {
		this.preselected = preselected;
	}

	/**
	 * Adds the to preselected.
	 *
	 * @param username
	 *            the username
	 */
	public void addToPreselected(String username) {
		if (!preselected.contains(username))
			preselected.add(username);
	}
}
