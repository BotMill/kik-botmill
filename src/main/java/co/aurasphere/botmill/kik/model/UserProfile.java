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

/**
 * The Class UserProfile.
 * 
 * @author Alvin P. Reyes
 */
public class UserProfile implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The profile pic url. */
	private String profilePicUrl;
	
	/** The profile pic last modified. */
	private String profilePicLastModified;
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the profile pic url.
	 *
	 * @return the profile pic url
	 */
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	
	/**
	 * Sets the profile pic url.
	 *
	 * @param profilePicUrl the new profile pic url
	 */
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	
	/**
	 * Gets the profile pic last modified.
	 *
	 * @return the profile pic last modified
	 */
	public String getProfilePicLastModified() {
		return profilePicLastModified;
	}
	
	/**
	 * Sets the profile pic last modified.
	 *
	 * @param profilePicLastModified the new profile pic last modified
	 */
	public void setProfilePicLastModified(String profilePicLastModified) {
		this.profilePicLastModified = profilePicLastModified;
	}
	
	
}
