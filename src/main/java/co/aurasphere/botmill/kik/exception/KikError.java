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
package co.aurasphere.botmill.kik.exception;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/**
 * Object that represents an error from Kik.
 * 
 * @author Alvin Reyes
 * 
 */
public class KikError implements Serializable {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The error message.
	 */
	private String message;

	/**
	 * The error type.
	 */
	private String type;

	/**
	 * The error code.
	 */
	private String code;

	/**
	 * The error Facebook's trace ID.
	 */
	@SerializedName("fbtrace_id")
	private String fbTraceId;

	/**
	 * Gets the {@link #message}.
	 *
	 * @return the {@link #message}.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the {@link #message}.
	 *
	 * @param message
	 *            the {@link #message} to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the {@link #type}.
	 *
	 * @return the {@link #type}.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the {@link #type}.
	 *
	 * @param type
	 *            the {@link #type} to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the {@link #code}.
	 *
	 * @return the {@link #code}.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the {@link #code}.
	 *
	 * @param code
	 *            the {@link #code} to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the {@link #fbTraceId}.
	 *
	 * @return the {@link #fbTraceId}.
	 */
	public String getFbTraceId() {
		return fbTraceId;
	}

	/**
	 * Sets the {@link #fbTraceId}.
	 *
	 * @param fbTraceId
	 *            the {@link #fbTraceId} to set.
	 */
	public void setFbTraceId(String fbTraceId) {
		this.fbTraceId = fbTraceId;
	}

}
