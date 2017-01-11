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

/**
 * The Class Features.
 */
public class Features implements Serializable {
   
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The manually send read receipts. */
	private boolean manuallySendReadReceipts;
	
	/** The receive read receipts. */
	private boolean receiveReadReceipts;
	
	/** The receive delivery receipts. */
	private boolean receiveDeliveryReceipts;
	
	/** The receive is typing. */
	private boolean receiveIsTyping;
	
	/**
	 * Checks if is manually send read receipts.
	 *
	 * @return true, if is manually send read receipts
	 */
	public boolean isManuallySendReadReceipts() {
		return manuallySendReadReceipts;
	}
	
	/**
	 * Sets the manually send read receipts.
	 *
	 * @param manuallySendReadReceipts the new manually send read receipts
	 */
	public void setManuallySendReadReceipts(boolean manuallySendReadReceipts) {
		this.manuallySendReadReceipts = manuallySendReadReceipts;
	}
	
	/**
	 * Checks if is receive read receipts.
	 *
	 * @return true, if is receive read receipts
	 */
	public boolean isReceiveReadReceipts() {
		return receiveReadReceipts;
	}
	
	/**
	 * Sets the receive read receipts.
	 *
	 * @param receiveReadReceipts the new receive read receipts
	 */
	public void setReceiveReadReceipts(boolean receiveReadReceipts) {
		this.receiveReadReceipts = receiveReadReceipts;
	}
	
	/**
	 * Checks if is receive delivery receipts.
	 *
	 * @return true, if is receive delivery receipts
	 */
	public boolean isReceiveDeliveryReceipts() {
		return receiveDeliveryReceipts;
	}
	
	/**
	 * Sets the receive delivery receipts.
	 *
	 * @param receiveDeliveryReceipts the new receive delivery receipts
	 */
	public void setReceiveDeliveryReceipts(boolean receiveDeliveryReceipts) {
		this.receiveDeliveryReceipts = receiveDeliveryReceipts;
	}
	
	/**
	 * Checks if is receive is typing.
	 *
	 * @return true, if is receive is typing
	 */
	public boolean isReceiveIsTyping() {
		return receiveIsTyping;
	}
	
	/**
	 * Sets the receive is typing.
	 *
	 * @param receiveIsTyping the new receive is typing
	 */
	public void setReceiveIsTyping(boolean receiveIsTyping) {
		this.receiveIsTyping = receiveIsTyping;
	}
}
