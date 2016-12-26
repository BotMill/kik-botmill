package co.aurasphere.botmill.kik.model;

import java.util.ArrayList;

/**
 * The Class FriendPicker.
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
		return Message.Type.FRIEND_PICKER;
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
