package co.aurasphere.botmill.kik.configuration;

import java.io.Serializable;

public class Features implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private boolean manuallySendReadReceipts;
	private boolean receiveReadReceipts;
	private boolean receiveDeliveryReceipts;
	private boolean receiveIsTyping;
	
	public boolean isManuallySendReadReceipts() {
		return manuallySendReadReceipts;
	}
	public void setManuallySendReadReceipts(boolean manuallySendReadReceipts) {
		this.manuallySendReadReceipts = manuallySendReadReceipts;
	}
	public boolean isReceiveReadReceipts() {
		return receiveReadReceipts;
	}
	public void setReceiveReadReceipts(boolean receiveReadReceipts) {
		this.receiveReadReceipts = receiveReadReceipts;
	}
	public boolean isReceiveDeliveryReceipts() {
		return receiveDeliveryReceipts;
	}
	public void setReceiveDeliveryReceipts(boolean receiveDeliveryReceipts) {
		this.receiveDeliveryReceipts = receiveDeliveryReceipts;
	}
	public boolean isReceiveIsTyping() {
		return receiveIsTyping;
	}
	public void setReceiveIsTyping(boolean receiveIsTyping) {
		this.receiveIsTyping = receiveIsTyping;
	}
}
