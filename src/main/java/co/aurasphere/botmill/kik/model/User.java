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
