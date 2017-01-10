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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.aurasphere.botmill.kik.constants.KikBotMillConstants;
import co.aurasphere.botmill.kik.network.Utils;
import co.aurasphere.botmill.kik.webhook.ThreadWebhookListener;

/**
 * The Class KikApi.
 * 
 * @author Alvin Reyes
 */
public class KikApi {

	/** The latest chats. */
	private HashMap<String, Chat> latestChats = new HashMap<String, Chat>();

	/** The api key. */
	private String username, apiKey;

	/** The listen port. */
	private short listenPort = 8080; // just a default.

	/** The bot instance. */
	private KikBot botInstance;

	/** The listener thread. */
	private ThreadWebhookListener listenerThread;

	/** The settings. */
	private Settings settings = new Settings(false, false, false, false);

	/**
	 * Builds the bot.
	 *
	 * @param username
	 *            the username
	 * @param apiKey
	 *            the api key
	 * @param listenPort
	 *            the listen port
	 * @param botInstance
	 *            the bot instance
	 * @return the kik api
	 */
	public static KikApi buildBot(String username, String apiKey, short listenPort, KikBot botInstance) {
		return new KikApi(username, apiKey, listenPort, botInstance);
	}

	/**
	 * Builds the bot.
	 *
	 * @param username
	 *            the username
	 * @param apiKey
	 *            the api key
	 * @param botInstance
	 *            the bot instance
	 * @return the kik api
	 */
	public static KikApi buildBot(String username, String apiKey, KikBot botInstance) {
		return new KikApi(username, apiKey, (short) (new Random().nextInt(Short.MAX_VALUE - 80) + 80), botInstance);
	}

	/**
	 * Instantiates a new kik api.
	 *
	 * @param username
	 *            the username
	 * @param apiKey
	 *            the api key
	 * @param listenPort
	 *            the listen port
	 * @param botInstance
	 *            the bot instance
	 */
	private KikApi(String username, String apiKey, short listenPort, KikBot botInstance) {
		this.username = username.toLowerCase();
		this.apiKey = apiKey;
		this.botInstance = botInstance;
		this.listenPort = listenPort;
	}

	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public Settings getSettings() {
		return settings;
	}

	/**
	 * Sets the settings.
	 *
	 * @param settings
	 *            the new settings
	 */
	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the apikey.
	 *
	 * @return the apikey
	 */
	public String getApikey() {
		return apiKey;
	}

	/**
	 * Gets the listener thread.
	 *
	 * @return the listener thread
	 */
	public ThreadWebhookListener getListenerThread() {
		return listenerThread;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the apikey.
	 *
	 * @param apikey
	 *            the new apikey
	 */
	public void setApikey(String apikey) {
		this.apiKey = apikey;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public short getPort() {
		return listenPort;
	}

	/**
	 * Handle webhook response.
	 *
	 * @param object
	 *            the object
	 */
	public void handleWebhookResponse(JsonObject object) {
		System.out.println("JSON: " + object.toString());
		if (object.has("messages")) {
			JsonArray msgArray = object.getAsJsonArray("messages");

			for (int i = 0; i < msgArray.size(); i++) {
				JsonObject message = msgArray.get(i).getAsJsonObject();

				String chatId = message.get("chatId").getAsString();
				String id = message.get("id").getAsString();
				String body = "";

				if (message.has("body")) {
					body = message.get("body").getAsString();
				}

				String type = message.get("type").getAsString();
				String from = message.get("from").getAsString();
				String picUrl = "", videoUrl = "";

				if (message.has("picUrl")) {
					picUrl = message.get("picUrl").getAsString();
					body = picUrl;
				}

				if (message.has("videoUrl")) {
					videoUrl = message.get("videoUrl").getAsString();
					body = videoUrl;
				}

				int timestamp = message.get("timestamp").getAsInt();
				boolean readReceiptRequested = message.get("readReceiptRequested").getAsBoolean();

				Chat chat = new Chat(chatId, Utils.getParticipants(message.get("participants").getAsJsonArray(), this),
						from, this);
				latestChats.put(from, chat);

				Message1 msg = new Message1(chatId, id, type, from, body, timestamp, readReceiptRequested, chat, this);

				if (type.equals(Message1.Type.IMAGE))
					msg.setPictureUrl(picUrl);

				if (type.equals(Message1.Type.VIDEO))
					msg.setVideoUrl(videoUrl);

				if (message.has("picked")) {
					JsonArray pickedArray = message.get("picked").getAsJsonArray();

					String[] picked = new String[pickedArray.size()];

					for (int j = 0; j < pickedArray.size(); j++) {
						picked[j] = pickedArray.get(j).getAsString();
					}

					msg.setPicked(picked);
				}

				botInstance.onMessage(msg);
			}
		} else {
			System.out.println("Json didnt have messages array!");
		}
	}

	/**
	 * Gets the latest chat for user.
	 *
	 * @param username
	 *            the username
	 * @return the latest chat for user
	 */
	public Chat getLatestChatForUser(String username) {
		return latestChats.get(username);
	}

	/**
	 * Checks if is latest chat for user avaliable.
	 *
	 * @param username
	 *            the username
	 * @return true, if is latest chat for user avaliable
	 */
	public boolean isLatestChatForUserAvaliable(String username) {
		return getLatestChatForUser(username) != null;
	}

	/**
	 * Gets the latest chats.
	 *
	 * @return the latest chats
	 */
	public HashMap<String, Chat> getLatestChats() {
		return latestChats;
	}

	/**
	 * Inits the.
	 *
	 * @param shouldBlock
	 *            the should block
	 * @throws Exception
	 *             the exception
	 */
	public void init(boolean shouldBlock) throws Exception {
		JsonObject requestObject = new JsonObject();
		requestObject.addProperty("webhook", "http://" + Utils.getPublicIP() + ":" + listenPort + "/" + username);

		JsonObject object = new JsonObject();
		object.addProperty("manuallySendReadReceipts", settings.manuallySendReadReceipts());
		object.addProperty("receiveReadReceipts", settings.receiveReadReceipts());
		object.addProperty("receiveDeliveryReceipts", settings.receiveDeliveryReceipts());
		object.addProperty("receiveIsTyping", settings.receiveIsTyping());

		requestObject.add("features", object);

		send(KikBotMillConstants.CONFIG_ENDPOINT, new Gson().toJson(requestObject), "post");
		send(KikBotMillConstants.CONFIG_ENDPOINT, "", "get");

		listenerThread = new ThreadWebhookListener(this);
		listenerThread.start();

		if (shouldBlock)
			listenerThread.join();
	}

	/**
	 * Gets the user info.
	 *
	 * @param username
	 *            the username
	 * @return the user info
	 * @throws Exception
	 *             the exception
	 */
	public JsonObject getUserInfo(String username) throws Exception {
		return new JsonParser().parse(send(KikBotMillConstants.USER_ENDPOINT + username, "", "get")).getAsJsonObject();
	}

	/**
	 * Send.
	 *
	 * @param url
	 *            the url
	 * @param data
	 *            the data
	 * @param method
	 *            the method
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String send(String url, String data, String method) throws Exception {
		URL obj = null;

		if (method.equals("both") || method.equals("get") && !data.isEmpty()) {
			obj = new URL(url + "?" + data);
		} else
			obj = new URL(url);

		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		con.setRequestProperty("Authorization",
				"Basic " + Base64.getEncoder().encodeToString((username + ":" + apiKey).getBytes("utf-8")));
		con.setRequestMethod(method.toUpperCase());
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

		if (method.equalsIgnoreCase("post")) {
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(data.getBytes("utf-8"));
			wr.flush();
			wr.close();
		}

		int responseCode = con.getResponseCode();
		System.out.println("\nSent '" + method.toUpperCase() + "' request to URL : " + url);
		System.out.println("Data : " + data);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine).append("\n");
		}
		in.close();
		System.out.println("Response text : " + response.toString());

		return response.toString();
	}
}
