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

import java.util.List;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.model.Frame;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessagePostback;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.model.Reply;
import co.aurasphere.botmill.kik.network.NetworkUtils;
import co.aurasphere.botmill.kik.outgoing.model.OutgoingMessage;
import co.aurasphere.botmill.kik.outgoing.reply.AnyReply;
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
		outgoingHandler(KikBotMillContext.getInstance().getBroadcastMessageActionFrames(), message, true);
		return this;
	}

	/**
	 * Process.
	 *
	 * @param message
	 *            the message
	 * @return the incoming to outgoing message handler
	 */
	public IncomingToOutgoingMessageHandler process(Message message) {
		// the responses are on a different bucket
		switch (message.getType()) {
		case TEXT:
			outgoingHandler(KikBotMillContext.getInstance().getTextMessageActionFrames(), message, false);
			break;
		case PICTURE:
			outgoingHandler(KikBotMillContext.getInstance().getMediaMessageActionFrames(), message, false);
			break;
		case VIDEO:
			outgoingHandler(KikBotMillContext.getInstance().getMediaMessageActionFrames(), message, false);
			break;
		case LINK:
			outgoingHandler(KikBotMillContext.getInstance().getLinkMessageActionFrames(), message, false);
			break;
		default:
			outgoingHandler(KikBotMillContext.getInstance().getActionFrames(), message, false);
			break;
		}

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
	private void outgoingHandler(List<Frame> actionFrames, Message message, boolean broadcast) {
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
						} else if (reply instanceof AnyReply) {
							// don't treat istyping, start chatting or any
							// non-text incoming message as any reply.
							if (!message.getType().equals(MessageType.IS_TYPING)
									|| !message.getType().equals(MessageType.START_CHATTING)
									|| !message.getType().equals(MessageType.SCAN_DATA)) {

								outgoingMessage = (OutgoingMessage) reply.processReply(message);

							}
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
