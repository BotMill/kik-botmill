/*
 * 
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
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
package co.aurasphere.botmill.kik.incoming.handler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import co.aurasphere.botmill.core.BotDefinition;
import co.aurasphere.botmill.core.annotation.Bot;
import co.aurasphere.botmill.core.internal.util.ConfigurationUtils;
import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.model.Event;
import co.aurasphere.botmill.kik.model.Frame;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessagePostback;
import co.aurasphere.botmill.kik.model.Reply;
import co.aurasphere.botmill.kik.outgoing.model.OutgoingMessage;
import co.aurasphere.botmill.kik.outgoing.reply.IsTypingReply;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.ReadReceiptReply;
import co.aurasphere.botmill.kik.outgoing.reply.TextMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.VideoMessageReply;
import co.aurasphere.botmill.kik.util.network.NetworkUtils;
import co.aurasphere.botmill.kik.incoming.event.AnyEvent;
import co.aurasphere.botmill.kik.incoming.event.DeliveryReceiptEvent;
import co.aurasphere.botmill.kik.incoming.event.EventType;
import co.aurasphere.botmill.kik.incoming.event.FriendPickerEvent;
import co.aurasphere.botmill.kik.incoming.event.IsTypingEvent;
import co.aurasphere.botmill.kik.incoming.event.LinkMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.MentionEvent;
import co.aurasphere.botmill.kik.incoming.event.PictureMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.ReadReceiptEvent;
import co.aurasphere.botmill.kik.incoming.event.ScanDataEvent;
import co.aurasphere.botmill.kik.incoming.event.StartChattingEvent;
import co.aurasphere.botmill.kik.incoming.event.StickerEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessagePatternEvent;
import co.aurasphere.botmill.kik.incoming.event.VideoMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.annotation.KikBotMillController;
import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;

/**
 * The Class IncomingToOutgoingMessageHandler.
 * 
 * This class handles the in between process of handling the incoming message
 * and outgoing messages. In the code of this class is a method that catches all
 * the POST request from Kik, convert that into Java Objects which is then pass
 * through the handler.
 * 
 * The handler checks for any matching conditions. This is done by iterating
 * through the list of action frames (from the specified domains). Once a match
 * is found, the designated Reply object is executed that constructs the
 * Outgoing message to Kik passing the ChatID and To values from the incoming
 * messages.
 * 
 * This is the core handler between Kik and the Kik-BotMill.
 * 
 * @author Alvin P. Reyes
 */
public class IncomingToOutgoingMessageHandler {

	protected static final Logger logger = LoggerFactory.getLogger(IncomingToOutgoingMessageHandler.class);

	/** The instance. */
	private static IncomingToOutgoingMessageHandler instance;

	/**
	 * Creates the handler.
	 *
	 * @return the incoming to outgoing message handler
	 */
	public static IncomingToOutgoingMessageHandler createHandler() {
		if (instance == null) {
			instance = new IncomingToOutgoingMessageHandler();
		}
		return instance;
	}

	/**
	 * Process broadcast.
	 *
	 * @param message
	 *            the message
	 * @return the incoming to outgoing message handler
	 */
	public IncomingToOutgoingMessageHandler processBroadcast(Message message) {
		handleOutgoingMessage(KikBotMillContext.getInstance().getBroadcastMessageActionFrames(), message, true);
		return this;
	}

	/**
	 * Process the incoming message.
	 *
	 * @param message
	 *            the message
	 * @return the incoming to outgoing message handler
	 */
	public IncomingToOutgoingMessageHandler process(Message message) {
		if (KikBotMillContext.getInstance().getActionFrames().size() > 0
				|| KikBotMillContext.getInstance().getAnyEventActionFrames().size() > 0) {
			List<Frame> actionFrames = new ArrayList<Frame>();
			actionFrames.addAll(KikBotMillContext.getInstance().getActionFrames());
			actionFrames.addAll(KikBotMillContext.getInstance().getAnyEventActionFrames());
			handleOutgoingMessage(actionFrames, message, false);
		}
		handleOutgoingMessage(message);
		return this;
	}

	/**
	 * Outgoing handler.
	 *
	 * @param actionFrames
	 *            the action frames
	 * @param message
	 *            the message
	 * @param broadcast
	 *            the broadcast
	 */
	private void handleOutgoingMessage(Message message) {
		// Tries to load and instantiate the bot definitions.
		for (BotDefinition defClass : ConfigurationUtils.getBotDefinitionInstance()) {
			// Check if it's annotated too.
			if (defClass.getClass().isAnnotationPresent(Bot.class)) {
				// check each method.
				for (Method method : defClass.getClass().getMethods()) {
					if (method.isAnnotationPresent(KikBotMillController.class)) {
						KikBotMillController botMillController = method.getAnnotation(KikBotMillController.class);
						try {
							String textOrPattern = "";
							if (!botMillController.text().equals("")) {
								textOrPattern = botMillController.text();
							} else {
								textOrPattern = botMillController.pattern();
							}
							Event event = toEvent(botMillController.eventType(), textOrPattern);

							if (event.verifyEvent((IncomingMessage) message)) {
								
								defClass.getClass().getSuperclass()
										.getDeclaredMethod("setIncomingMessage", IncomingMessage.class)
										.invoke(defClass, ((IncomingMessage) message)); 
								
								defClass.getClass().getSuperclass()
										.getDeclaredMethod("setEvent", Event.class)
										.invoke(defClass, event);
								
								method.invoke(defClass, message); // then
																	// invoke.
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		}
	}

	/**
	 * Outgoing handler.
	 *
	 * @param actionFrames
	 *            the action frames
	 * @param message
	 *            the message
	 * @param broadcast
	 *            the broadcast
	 */
	private void handleOutgoingMessage(List<Frame> actionFrames, Message message, boolean broadcast) {
		MessagePostback postback = null;
		if (actionFrames.size() > 0) {
			for (Frame frame : actionFrames) {
				if (frame.getEvent().verifyEvent(((IncomingMessage) message))) {
					postback = new MessagePostback();
					for (Reply<? extends Message> reply : frame.getReplies()) {
						OutgoingMessage outgoingMessage = null;
						if (reply instanceof TextMessageReply) {
							outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.TextMessage();
							outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.TextMessage) reply
									.processReply(message);
						} else if (reply instanceof PictureMessageReply) {
							outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.PictureMessage();
							outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.PictureMessage) reply
									.processReply(message);
						} else if (reply instanceof LinkMessageReply) {
							outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.LinkMessage();
							outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.LinkMessage) reply
									.processReply(message);
						} else if (reply instanceof VideoMessageReply) {
							outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.VideoMessage();
							outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.VideoMessage) reply
									.processReply(message);
						} else if (reply instanceof IsTypingReply) {
							outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage();
							outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage) reply
									.processReply(message);
						} else if (reply instanceof ReadReceiptReply) {
							outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage();
							outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage) reply
									.processReply(message);
						}

						// We can't set a null outgoing message.
						if (outgoingMessage != null) {
							outgoingMessage.setTo(((IncomingMessage) message).getFrom());
							outgoingMessage.setChatId(message.getChatId());
							postback.addMessage(outgoingMessage);
						}
					}
					break;
				}
			}
			if (postback != null) {
				if (broadcast) {
					NetworkUtils.postJsonMessageBroadcast(postback);
				} else {
					NetworkUtils.postJsonMessage(postback);
				}
			}
		}
	}

	private Event toEvent(EventType eventType, String textOrPattern) {
		switch (eventType) {
		case ANY:
			return new AnyEvent();
		case DELIVERY_RECEIPT:
			return new DeliveryReceiptEvent();
		case FRIEND_PICKER:
			return new FriendPickerEvent();
		case IS_TYPING:
			return new IsTypingEvent();
		case LINK:
			return new LinkMessageEvent();
		case MENTION:
			return new MentionEvent();
		case PICTURE:
			return new PictureMessageEvent();
		case SCAN_DATA:
			return new ScanDataEvent();
		case START_CHATTING:
			return new StartChattingEvent();
		case STICKER:
			return new StickerEvent();
		case TEXT_MESSAGE:
			return new TextMessageEvent().setText(textOrPattern);
		case TEXT_PATTERN:
			return new TextMessagePatternEvent().setPattern(textOrPattern);
		case VIDEO:
			return new VideoMessageEvent();
		case READ_RECEIPT:
			return new ReadReceiptEvent();
		}
		return null; // it's impossible to have a null event, but if it does
						// happen, it will be ignored on the handler.
	}
}
