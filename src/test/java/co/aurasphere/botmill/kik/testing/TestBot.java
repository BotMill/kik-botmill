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
package co.aurasphere.botmill.kik.testing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import co.aurasphere.botmill.kik.model.Chat;
import co.aurasphere.botmill.kik.model.FileMessage;
import co.aurasphere.botmill.kik.model.FriendPicker;
import co.aurasphere.botmill.kik.model.Keyboard;
import co.aurasphere.botmill.kik.model.KikApi;
import co.aurasphere.botmill.kik.model.KikBot;
import co.aurasphere.botmill.kik.model.Message1;
import co.aurasphere.botmill.kik.model.Settings;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;

/**
 * The Class TestBot.
 */
public class TestBot extends KikBot {

	/** The bot. */
	public static KikApi bot;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		bot = KikApi.buildBot("heyjayreyes", "0427ae1f-0d2c-490d-8053-4f397b612161", (short) 8080, new TestBot());
		bot.setSettings(new Settings(false, false, false, false)); // manuallySendReadReceipts,
																	// receiveReadReceipts,
																	// receiveDeliveryReceipts,
																	// receiveIsTyping
		bot.init(false); // Should the init block?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thub.kik.botstack.KikBot#onTextReceived(com.thub.kik.botstack.
	 * Message)
	 */
	@Override
	public void onTextReceived(Message1 msg) {
		List<Object> objects = new ArrayList<Object>();
		objects.add("hi");
		objects.add("no");
		objects.add(new FriendPicker("Select 2-5 friends", 2, 5));

		Keyboard keyboard = new Keyboard(objects);

		msg.getChat().sendMessage(new Message1("Hi there!", Message1.Type.TEXT).setKeyboard(keyboard));

		Iterator<Map.Entry<String, Chat>> it = bot.getLatestChats().entrySet().iterator(); // bot.getLatestChats()
																							// //
																							// to
																							// users.
		while (it.hasNext()) // The code in this codeblock sends the text that
								// it received to everyone.
		{
			Map.Entry<String, Chat> pair = (Map.Entry<String, Chat>) it.next();
			pair.getValue().sendMessage(new Message1(msg.getBody(), Message1.Type.TEXT));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thub.kik.botstack.KikBot#onFriendPickedReceived(com.thub.kik.botstack
	 * .Message)
	 */
	@Override
	public void onFriendPickedReceived(Message1 msg) {
		msg.getChat()
				.sendMessage(new Message1(
						"You chose these friends: " + Arrays.stream(msg.getPicked()).collect(Collectors.joining(" ")),
						Message1.Type.TEXT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thub.kik.botstack.KikBot#onImageReceived(com.thub.kik.botstack.
	 * Message)
	 */
	@Override
	public void onImageReceived(Message1 msg) {
		msg.getChat().sendMessage(new Message1("You sent this image", Message1.Type.TEXT),
				new Message1(msg.getPictureUrl(), Message1.Type.IMAGE));
		msg.getChat().sendMessage(new FileMessage(new File("images/test.png"), bot, Message1.Type.IMAGE, 4)); // can
																												// be
																												// used
																												// to
																												// send
																												// images
																												// from
																												// a
																												// File,
																												// the
																												// last
																												// parameter
																												// is
																												// the
																												// type
																												// time.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thub.kik.botstack.KikBot#onVideoReceived(com.thub.kik.botstack.
	 * Message)
	 */
	@Override
	public void onVideoReceived(Message1 msg) {
		msg.getChat().sendMessage(new Message1("You sent this video", Message1.Type.TEXT),
				new Message1(msg.getVideoUrl(), Message1.Type.VIDEO));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thub.kik.botstack.KikBot#onUrlReceived(com.thub.kik.botstack.Message)
	 */
	@Override
	public void onUrlReceived(Message1 msg) {
		msg.getChat().sendMessage(new Message1("You sent this URL", Message1.Type.TEXT),
				new Message1(msg.getBody(), Message1.Type.URL));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thub.kik.botstack.KikBot#onStartChattingReceived(com.thub.kik.
	 * botstack.Message)
	 */
	@Override
	public void onStartChattingReceived(Message1 msg) {
		msg.getChat().sendMessage(new Message1("Welcome!", Message1.Type.TEXT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thub.kik.botstack.KikBot#onUserTypingReceived(com.thub.kik.botstack.
	 * Message)
	 */
	@Override
	public void onUserTypingReceived(Message1 msg) {
		System.out.println(msg.getFrom().getUsername() + " started typing.");
	}
}
