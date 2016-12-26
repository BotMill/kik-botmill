package co.aurasphere.botmill.kik.model;

/**
 * The Class Settings.
 */
public class Settings {

	/** The receive is typing. */
	private boolean manuallySendReadReceipts, receiveReadReceipts, receiveDeliveryReceipts, receiveIsTyping;

	/**
	 * Instantiates a new settings.
	 *
	 * @param manuallySendReadReceipts
	 *            the manually send read receipts
	 * @param receiveReadReceipts
	 *            the receive read receipts
	 * @param receiveDeliveryReceipts
	 *            the receive delivery receipts
	 * @param receiveIsTyping
	 *            the receive is typing
	 */
	public Settings(boolean manuallySendReadReceipts, boolean receiveReadReceipts, boolean receiveDeliveryReceipts,
			boolean receiveIsTyping) {
		this.manuallySendReadReceipts = manuallySendReadReceipts;
		this.receiveReadReceipts = receiveReadReceipts;
		this.receiveDeliveryReceipts = receiveDeliveryReceipts;
		this.receiveIsTyping = receiveIsTyping;
	}

	/**
	 * Manually send read receipts.
	 *
	 * @return true, if successful
	 */
	public boolean manuallySendReadReceipts() {
		return manuallySendReadReceipts;
	}

	/**
	 * Receive read receipts.
	 *
	 * @return true, if successful
	 */
	public boolean receiveReadReceipts() {
		return receiveReadReceipts;
	}

	/**
	 * Receive delivery receipts.
	 *
	 * @return true, if successful
	 */
	public boolean receiveDeliveryReceipts() {
		return receiveDeliveryReceipts;
	}

	/**
	 * Receive is typing.
	 *
	 * @return true, if successful
	 */
	public boolean receiveIsTyping() {
		return receiveIsTyping;
	}

	/**
	 * Sets the manually send read receipts.
	 *
	 * @param manuallySendReadReceipts
	 *            the new manually send read receipts
	 */
	public void setManuallySendReadReceipts(boolean manuallySendReadReceipts) {
		this.manuallySendReadReceipts = manuallySendReadReceipts;
	}

	/**
	 * Sets the receive read receipts.
	 *
	 * @param receiveReadReceipts
	 *            the new receive read receipts
	 */
	public void setReceiveReadReceipts(boolean receiveReadReceipts) {
		this.receiveReadReceipts = receiveReadReceipts;
	}

	/**
	 * Sets the receive delivery receipts.
	 *
	 * @param receiveDeliveryReceipts
	 *            the new receive delivery receipts
	 */
	public void setReceiveDeliveryReceipts(boolean receiveDeliveryReceipts) {
		this.receiveDeliveryReceipts = receiveDeliveryReceipts;
	}

	/**
	 * Sets the receive is typing.
	 *
	 * @param receiveIsTyping
	 *            the new receive is typing
	 */
	public void setReceiveIsTyping(boolean receiveIsTyping) {
		this.receiveIsTyping = receiveIsTyping;
	}
}
