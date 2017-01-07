package co.aurasphere.botmill.kik.model;

/**
 * The Class KikBot.
 * 
 * @author Alvin Reyes
 */
public class KikBot {

	/**
	 * On text received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onTextReceived(Message1 msg) {
	}

	/**
	 * On image received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onImageReceived(Message1 msg) {
	}

	/**
	 * On video received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onVideoReceived(Message1 msg) {
	}

	/**
	 * On url received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onUrlReceived(Message1 msg) {
	}

	/**
	 * On start chatting received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onStartChattingReceived(Message1 msg) {
	}

	/**
	 * On user typing received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onUserTypingReceived(Message1 msg) {
	}

	/**
	 * On friend picked received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onFriendPickedReceived(Message1 msg) {
	}

	/**
	 * On message.
	 *
	 * @param message
	 *            the message
	 */
	public void onMessage(Message1 message) {
		switch (message.getType()) {
		case Message1.Type.TEXT:
			onTextReceived(message);
			break;

		case Message1.Type.IMAGE:
			onImageReceived(message);
			break;

		case Message1.Type.VIDEO:
			onVideoReceived(message);
			break;

		case Message1.Type.URL:
			onUrlReceived(message);
			break;

		case Message1.Type.START_CHATTING:
			onStartChattingReceived(message);
			break;

		case Message1.Type.IS_TYPING:
			onUserTypingReceived(message);
		case Message1.Type.FRIEND_PICKER:
			onFriendPickedReceived(message);
			break;

		default:
			throw new RuntimeException("The message type " + message.getType() + " was not handled.");
		}
	}

}
