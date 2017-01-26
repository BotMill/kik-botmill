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
package co.aurasphere.botmill.kik.incoming.handler;

import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.model.Frame;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessagePostback;
import co.aurasphere.botmill.kik.model.Reply;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.outgoing.model.OutgoingMessage;
import co.aurasphere.botmill.kik.outgoing.reply.IsTypingReply;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.ReadReceiptReply;
import co.aurasphere.botmill.kik.outgoing.reply.TextMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.VideoMessageReply;
import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;

/**
 * The Class IncomingToOutgoingMessageHandler.
 * 
 * This class handles the in between process of handling the incoming message and outgoing
 * messages. In the code of this class is a method that catches all the POST request from
 * Kik, convert that into Java Objects which is then pass through the handler.
 * 
 * The handler checks for any matching conditions. This is done by iterating through the list of
 * action frames (from the specified domains). Once a match is found, the designated Reply object
 * is executed that constructs the Outgoing message to Kik passing the ChatID and To values 
 * from the incoming messages.
 * 
 * This is the core handler between Kik and the Kik-BotMill.
 * @author Alvin P. Reyes
 */
public class IncomingToOutgoingMessageHandler {

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
		List<Frame> actionFrames = new ArrayList<Frame>();
		actionFrames.addAll(KikBotMillContext.getInstance().getActionFrames());
		actionFrames.addAll(KikBotMillContext.getInstance().getAnyEventActionFrames());
		handleOutgoingMessage(actionFrames, message, false);
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
						
						//	We can't set a null outgoing message.
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
}
