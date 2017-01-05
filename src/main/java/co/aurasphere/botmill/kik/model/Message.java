package co.aurasphere.botmill.kik.model;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * The Class Message.
 * 
 * @author Alvin Reyes
 */
public class Message {

	/** The video url. */
	public String id, type, body, pictureUrl, videoUrl;

	/** The from. */
	private User from;

	/** The timestamp. */
	private int timestamp;

	/** The read receipt requested. */
	private boolean readReceiptRequested;

	/** The chat. */
	private Chat chat;

	/** The keyboard. */
	private Keyboard keyboard = null;

	/** The picked. */
	private String[] picked;

	/** The type time. */
	private int typeTime;

	/**
	 * Instantiates a new message.
	 *
	 * @param msg
	 *            the msg
	 * @param type
	 *            the type
	 * @param typeTime
	 *            the type time
	 */
	public Message(String msg, String type, int typeTime) {
		this.body = msg;
		this.type = type;
		this.typeTime = typeTime;
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param msg
	 *            the msg
	 * @param type
	 *            the type
	 */
	public Message(String msg, String type) {
		this(msg, type, msg.length());
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param chatId
	 *            the chat id
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @param from
	 *            the from
	 * @param body
	 *            the body
	 * @param timestamp
	 *            the timestamp
	 * @param readReceiptRequested
	 *            the read receipt requested
	 * @param chat
	 *            the chat
	 * @param kikApi
	 *            the kik api
	 */
	public Message(String chatId, String id, String type, String from, String body, int timestamp,
			boolean readReceiptRequested, Chat chat, KikApi kikApi) {
		this.id = id;
		this.type = type;
		this.from = new User(from, kikApi);
		this.body = body;
		this.timestamp = timestamp;
		this.readReceiptRequested = readReceiptRequested;
		this.chat = chat;
	}

	/**
	 * Gets the picture url.
	 *
	 * @return the picture url
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * Sets the picture url.
	 *
	 * @param pictureUrl
	 *            the new picture url
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * Sets the video url.
	 *
	 * @param videoUrl
	 *            the new video url
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	/**
	 * Gets the video url.
	 *
	 * @return the video url
	 */
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * Gets the chat.
	 *
	 * @return the chat
	 */
	public Chat getChat() {
		return chat;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the type time.
	 *
	 * @return the type time
	 */
	public int getTypeTime() {
		return typeTime;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public User getFrom() {
		return from;
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
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public int getTimestamp() {
		return timestamp;
	}

	/**
	 * Checks if is read receipt requested.
	 *
	 * @return true, if is read receipt requested
	 */
	public boolean isReadReceiptRequested() {
		return readReceiptRequested;
	}

	/**
	 * The Class Type.
	 */
	public class Type {

		/** The Constant SCAN_DATA. */
		public static final String TEXT = "text", FRIEND_PICKER = "friend-picker", STICKER = "sticker",
				DELIVERY_RECEIPT = "delivery-receipt", URL = "link", IMAGE = "picture", IS_TYPING = "is-typing",
				START_CHATTING = "start-chatting", READ_RECEPT = "read-receipt", VIDEO = "video",
				SCAN_DATA = "scan-data";
	}

	/**
	 * Gets the keyboard.
	 *
	 * @return the keyboard
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}

	/**
	 * Checks for keyboard.
	 *
	 * @return true, if successful
	 */
	public boolean hasKeyboard() {
		return keyboard != null;
	}

	/**
	 * Sets the keyboard.
	 *
	 * @param keyboard
	 *            the keyboard
	 * @return the message
	 */
	public Message setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
		return this;
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
	 * Sets the picked.
	 *
	 * @param picked
	 *            the new picked
	 */
	public void setPicked(String[] picked) {
		this.picked = picked;
	}

	/**
	 * Gets the picked.
	 *
	 * @return the picked
	 */
	public String[] getPicked() {
		return this.picked;
	}

	/**
	 * Send read recepit.
	 */
	public void sendReadRecepit() {
		JsonArray messageid = new JsonArray();
		messageid.add(this.getId());
		JsonObject main = new JsonObject();
		main.addProperty("chatId", this.getChat().getChatId());
		main.addProperty("type", Message.Type.READ_RECEPT);
		main.addProperty("to", this.getFrom().getUsername());
		main.addProperty("id", UUID.randomUUID().toString());
		main.add("messageIds", messageid);
		JsonArray messages = new JsonArray();
		messages.add(main);
		JsonObject object = new JsonObject();
		object.add("messages", messages);

		try {
			getFrom().getKikApi().send(KikBotMillConstants.MESSAGE_ENDPOINT, new Gson().toJson(object), "post");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
