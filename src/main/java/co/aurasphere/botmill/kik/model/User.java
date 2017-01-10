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

import com.google.gson.JsonObject;

/**
 * The Class User.
 * 
 * @author Alvin Reyes
 */
public class User {

	/** The profile pic url. */
	private String username = "", firstName = "", lastName = "", profilePicUrl = "";

	/** The kik api. */
	private KikApi kikApi;

	/**
	 * Instantiates a new user.
	 *
	 * @param username
	 *            the username
	 * @param kikApi
	 *            the kik api
	 */
	public User(String username, KikApi kikApi) {
		this.username = username;
		this.kikApi = kikApi;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param username
	 *            the username
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param profilePicUrl
	 *            the profile pic url
	 */
	public User(String username, String firstName, String lastName, String profilePicUrl) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicUrl = profilePicUrl;
	}

	/**
	 * Gets the profile.
	 *
	 * @return the profile
	 */
	private void getProfile() {
		try {
			JsonObject object = kikApi.getUserInfo(username);
			firstName = object.get("firstName").getAsString();
			lastName = object.get("lastName").getAsString();
			profilePicUrl = object.get("profilePicUrl").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		if (firstName.isEmpty())
			getProfile();

		return firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		if (lastName.isEmpty())
			getProfile();

		return lastName;
	}

	/**
	 * Gets the profile pic url.
	 *
	 * @return the profile pic url
	 */
	public String getProfilePicUrl() {
		if (profilePicUrl.isEmpty())
			getProfile();

		return profilePicUrl;
	}

	/**
	 * Gets the kik api.
	 *
	 * @return the kik api
	 */
	public KikApi getKikApi() {
		return kikApi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		getProfile();

		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", profilePicUrl="
				+ profilePicUrl + ", kikApi=" + kikApi + "]";
	}
}
