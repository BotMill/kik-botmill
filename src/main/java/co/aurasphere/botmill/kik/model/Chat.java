package co.aurasphere.botmill.kik.model;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * The Class Chat.
 * 
 * @author Alvin Reyes
 * 
 */
public class Chat {

	/** The from. */
	private String chatId, from;

	/** The participants. */
	private ArrayList<User> participants = new ArrayList<User>();

	/** The kik api. */
	private KikApi kikApi;

	/**
	 * Instantiates a new chat.
	 *
	 * @param chatId
	 *            the chat id
	 * @param participants
	 *            the participants
	 * @param from
	 *            the from
	 * @param kikApi
	 *            the kik api
	 */
	public Chat(String chatId, ArrayList<User> participants, String from, KikApi kikApi) {
		this.chatId = chatId;
		this.participants = participants;
		this.from = from;
		this.kikApi = kikApi;
	}

	/**
	 * Gets the chat id.
	 *
	 * @return the chat id
	 */
	public String getChatId() {
		return chatId;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Gets the participants.
	 *
	 * @return the participants
	 */
	public ArrayList<User> getParticipants() {
		return participants;
	}

	/**
	 * Send message.
	 *
	 * @param msgs
	 *            the msgs
	 */
	public void sendMessage(Message... msgs) {
		JsonObject msgObject = new JsonObject();
		JsonArray msgArray = new JsonArray();

		for (int i = 0; i < msgs.length; i++) {
			JsonObject msg = new JsonObject();

			msg.addProperty("chatId", chatId);
			msg.addProperty("type", msgs[i].getType());
			msg.addProperty("to", from);

			if (msgs[i].hasKeyboard()) {
				msg.add("keyboards", msgs[i].getKeyboard().getKeyboardJson());
			}

			switch (msgs[i].getType()) {
			case Message.Type.IMAGE:
				msg.addProperty("picUrl", msgs[i].getBody());
				break;

			case Message.Type.URL:
				msg.addProperty("url", msgs[i].getBody());
				break;

			case Message.Type.VIDEO:
				msg.addProperty("videoUrl", msgs[i].getBody());
				break;

			default:
				if (msgs[i].getBody().isEmpty()) {
					System.out.println("Message " + i + "'s body was empty, it had the type of " + msgs[i].getType());

					continue;
				}
				msg.addProperty("body", msgs[i].getBody());
				break;
			}

			if (msgs[i].getTypeTime() > 0) {
				msg.addProperty("typeTime", msgs[i].getTypeTime());
			}

			msgArray.add(msg);
		}

		msgObject.add("messages", msgArray);

		try {
			kikApi.send(KikBotMillConstants.MESSAGE_ENDPOINT, msgObject.toString(), "post");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
