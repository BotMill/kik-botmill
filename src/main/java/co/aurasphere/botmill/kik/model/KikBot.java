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
	public void onTextReceived(Message msg) {
	}

	/**
	 * On image received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onImageReceived(Message msg) {
	}

	/**
	 * On video received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onVideoReceived(Message msg) {
	}

	/**
	 * On url received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onUrlReceived(Message msg) {
	}

	/**
	 * On start chatting received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onStartChattingReceived(Message msg) {
	}

	/**
	 * On user typing received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onUserTypingReceived(Message msg) {
	}

	/**
	 * On friend picked received.
	 *
	 * @param msg
	 *            the msg
	 */
	public void onFriendPickedReceived(Message msg) {
	}

	/**
	 * On message.
	 *
	 * @param message
	 *            the message
	 */
	public void onMessage(Message message) {
		switch (message.getType()) {
		case Message.Type.TEXT:
			onTextReceived(message);
			break;

		case Message.Type.IMAGE:
			onImageReceived(message);
			break;

		case Message.Type.VIDEO:
			onVideoReceived(message);
			break;

		case Message.Type.URL:
			onUrlReceived(message);
			break;

		case Message.Type.START_CHATTING:
			onStartChattingReceived(message);
			break;

		case Message.Type.IS_TYPING:
			onUserTypingReceived(message);
		case Message.Type.FRIEND_PICKER:
			onFriendPickedReceived(message);
			break;

		default:
			throw new RuntimeException("The message type " + message.getType() + " was not handled.");
		}
	}

}
