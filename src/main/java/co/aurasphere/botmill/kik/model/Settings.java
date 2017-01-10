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

/**
 * The Class Settings.
 * 
 * @author Alvin Reyes
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
