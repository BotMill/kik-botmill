package co.aurasphere.botmill.kik.exception;

import java.io.Serializable;

/**
 * Message which contains a {@link KikError}.
 * 
 * @author Alvin Reyes
 * 
 */
public class KikErrorMessage implements Serializable {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The error from Facebook.
	 */
	private KikError error;

	/**
	 * Gets the {@link #error}.
	 *
	 * @return the {@link #error}.
	 */
	public KikError getError() {
		return error;
	}

	/**
	 * Sets the {@link #error}.
	 *
	 * @param error
	 *            the {@link #error} to set.
	 */
	public void setError(KikError error) {
		this.error = error;
	}

}
