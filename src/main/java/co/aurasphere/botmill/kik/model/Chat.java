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

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import co.aurasphere.botmill.kik.constants.KikBotMillConstants;

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
	public void sendMessage(Message1... msgs) {
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
			case Message1.Type.IMAGE:
				msg.addProperty("picUrl", msgs[i].getBody());
				break;

			case Message1.Type.URL:
				msg.addProperty("url", msgs[i].getBody());
				break;

			case Message1.Type.VIDEO:
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
