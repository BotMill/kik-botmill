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
package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Response.
 * 
 * @author Alvin P. Reyes
 */
public class Response implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The body. */
	private String body;
	
	/** The type. */
	private ResponseType type;
	
	/** The min. */
	private Integer min;
	
	/** The max. */
	private Integer max;
	
	/** The preselected. */
	private List<String> preselected;
	
	/**
	 * Instantiates a new response.
	 *
	 * @param body the body
	 * @param type the type
	 */
	public Response(String body, ResponseType type) {
		this.body = body;
		this.type = type;
	}
	
	public Response(String body, ResponseType type, int min, int max, List<String> preselected) {
		this.body = body;
		this.type = type;
		this.min = min;
		this.max = max;
		this.preselected = preselected;
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
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ResponseType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(ResponseType type) {
		this.type = type;
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
	 * Sets the min.
	 *
	 * @param min the new min
	 */
	public void setMin(Integer min) {
		this.min = min;
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
	 * Sets the max.
	 *
	 * @param max the new max
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * Gets the preselected.
	 *
	 * @return the preselected
	 */
	public List<String> getPreselected() {
		return preselected;
	}

	/**
	 * Sets the preselected.
	 *
	 * @param preselected the new preselected
	 */
	public void setPreselected(List<String> preselected) {
		this.preselected = preselected;
	}
	
	
}
