package co.aurasphere.botmill.kik.model;

import java.io.Serializable;

public class UserProfile implements Serializable {

	private String firstName;
	private String lastName;
	private String profilePicUrl;
	private String profilePicLastModified;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	public String getProfilePicLastModified() {
		return profilePicLastModified;
	}
	public void setProfilePicLastModified(String profilePicLastModified) {
		this.profilePicLastModified = profilePicLastModified;
	}
	
	
}
