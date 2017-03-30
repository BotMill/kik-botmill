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
package co.aurasphere.botmill.kik.model;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import co.aurasphere.botmill.core.BotDefinition;
import co.aurasphere.botmill.core.BotMillSession;
import co.aurasphere.botmill.core.internal.util.ConfigurationUtils;
import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.exception.BotMillMissingConfigurationException;
import co.aurasphere.botmill.kik.exception.KikBotMillException;
import co.aurasphere.botmill.kik.incoming.event.AnyEvent;
import co.aurasphere.botmill.kik.incoming.event.DeliveryReceiptEvent;
import co.aurasphere.botmill.kik.incoming.event.KikBotMillEventType;
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
import co.aurasphere.botmill.kik.incoming.event.annotation.KikBotMillInit;
import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;
import co.aurasphere.botmill.kik.outgoing.model.OutgoingMessage;
import co.aurasphere.botmill.kik.outgoing.reply.IsTypingReply;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.ReadReceiptReply;
import co.aurasphere.botmill.kik.outgoing.reply.TextMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.VideoMessageReply;
import co.aurasphere.botmill.kik.util.network.NetworkUtils;
import co.aurasphere.botmill.kik.util.properties.PropertiesUtil;

/**
 * The Class AbstractDomain.
 * 
 * @author Alvin P. Reyes
 */
public abstract class KikBot implements BotDefinition {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(KikBot.class);
	
	/** The Constant KIK_BOTMILL_USER_NAME_PROP. */
	private static final String KIK_BOTMILL_USER_NAME_PROP = "kik.user.name";
	
	/** The Constant KIK_BOTMILL_API_KEY_PROP. */
	private static final String KIK_BOTMILL_API_KEY_PROP = "kik.api.key";
	
	/** The Constant KIK_BOTMILL_USER_NAME_PROPERTY. */
	private static final String KIK_BOTMILL_USER_NAME_PROPERTY = "USERNAME";
	
	/** The Constant KIK_BOTMILL_API_KEY_PROPERTY. */
	private static final String KIK_BOTMILL_API_KEY_PROPERTY = "API_KEY";
	
	/** The Constant KIK_BOTMILL_USER_NAME_PROP_PHOLDER. */
	private static final String KIK_BOTMILL_USER_NAME_PROP_PHOLDER = "<USERNAME>";
	
	/** The Constant KIK_BOTMILL_API_KEY_PROP_PHOLDER. */
	private static final String KIK_BOTMILL_API_KEY_PROP_PHOLDER = "<API_KEY>";

	/** The action frame. */
	private ActionFrame actionFrame;

	/**  The Botmill sesssion *. */
	private BotMillSession botMillSession;

	/** The incoming message. */
	protected IncomingMessage incomingMessage;
	
	/** The event **/
	protected Event event;
	
	/**
	 * Sets the incoming message.
	 *
	 * @param incomingMessage the new incoming message
	 */
	public void setIncomingMessage(IncomingMessage incomingMessage) {
		this.incomingMessage = incomingMessage;
	}
	
	/**
	 * Set the incoming event.
	 * 
	 * @param event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * Instantiates a new abstract domain.
	 */
	public KikBot() {
		try {
			this.buildKikBotConfig();
			this.buildAnnotatedInitDomain();
			this.buildAnnotatedBehaviour();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	protected void startConversation(IncomingMessage message) {
		KikBotMillContext.getInstance().setUserConversation(message.getFrom(), true);
	}
	
	protected void endConversation(IncomingMessage message) {
		KikBotMillContext.getInstance().setUserConversation(message.getFrom(), false);
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.core.BotDefinition#defineBehaviour()
	 */
	@Override
	public void defineBehaviour() {}

	/**
	 * Builds the annotated init domain.
	 */
	private void buildAnnotatedInitDomain() {
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(KikBotMillInit.class)) {
				try {
					method.invoke(this);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Builds the annotated domain.
	 *
	 * @throws KikBotMillException
	 *             the kik bot mill exception
	 */
	private void buildAnnotatedBehaviour() throws KikBotMillException {
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(KikBotMillController.class) &&  method.getParameterTypes().length == 0) {
				KikBotMillController botMillController = method.getAnnotation(KikBotMillController.class);
				try {
					actionFrame = new ActionFrame();
					String textOrPattern = "";
					if (!botMillController.text().equals("")) {
						textOrPattern = botMillController.text();
					} else {
						textOrPattern = botMillController.pattern();
					}
					// set the event.
					actionFrame.setEvent(toEvent(botMillController.eventType(), textOrPattern));
					method.invoke(this); // invoke the method.

					// add the action frame to the context.
					KikBotMillContext.getInstance().addActionFrameToContext(actionFrame);
					
					// add to method map.
					KikBotMillContext.getInstance().addToConvoMap(method.getName(), botMillController.next());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Builds the kik bot config.
	 *
	 * @throws BotMillMissingConfigurationException
	 *             the bot mill missing configuration exception
	 */
	private void buildKikBotConfig() throws BotMillMissingConfigurationException {
		// Everything goes well, initialize the setup.
		KikBotMillContext.getInstance().setup(
				ConfigurationUtils.getEncryptedConfiguration().getProperty(KIK_BOTMILL_USER_NAME_PROP),
				ConfigurationUtils.getEncryptedConfiguration().getProperty(KIK_BOTMILL_API_KEY_PROP));

		// Create the botmill session.
		botMillSession = BotMillSession.getInstance();
	}


	/**
	 * Bot mill session.
	 *
	 * @return the bot mill session
	 */
	protected final BotMillSession botMillSession() {
		return this.botMillSession;
	}

	/**
	 * Reply.
	 *
	 * @param reply the reply
	 */
	protected final void reply(Reply<? extends Message> reply) {
		procesReply(reply, this.incomingMessage);
	}
	
	/**
	 * Adds the reply.
	 *
	 * @param reply the reply
	 */
	protected final void addReply(Reply<? extends Message> reply) {
		if(actionFrame == null) {
			actionFrame = new ActionFrame();
			actionFrame.setEvent(event);
		}
		
		actionFrame.addReply(reply);
	}
	
	/**
	 * Execute replies.
	 */
	protected final void executeReplies() {
		if (actionFrame.getEvent().verifyEvent(this.incomingMessage)) {
			for (Reply<? extends Message> reply : actionFrame.getReplies()) {
				procesReply(reply,this.incomingMessage);
			}
			actionFrame = null;
		}
	}

	/**
	 * Process reply.
	 *
	 * @param reply the reply
	 * @param message the message
	 */
	private final void procesReply(Reply<? extends Message> reply, IncomingMessage message) {

		MessagePostback postback = new MessagePostback();
		OutgoingMessage outgoingMessage = null;

		if (reply instanceof TextMessageReply) {
			outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.TextMessage();
			outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.TextMessage) reply.processReply(message);
		} else if (reply instanceof PictureMessageReply) {
			outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.PictureMessage();
			outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.PictureMessage) reply.processReply(message);
		} else if (reply instanceof LinkMessageReply) {
			outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.LinkMessage();
			outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.LinkMessage) reply.processReply(message);
		} else if (reply instanceof VideoMessageReply) {
			outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.VideoMessage();
			outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.VideoMessage) reply.processReply(message);
		} else if (reply instanceof IsTypingReply) {
			outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage();
			outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage) reply.processReply(message);
		} else if (reply instanceof ReadReceiptReply) {
			outgoingMessage = new co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage();
			outgoingMessage = (co.aurasphere.botmill.kik.outgoing.model.ReadReceiptMessage) reply.processReply(message);
		}

		// We can't set a null outgoing message.
		if (outgoingMessage != null) {
			outgoingMessage.setTo(((IncomingMessage) message).getFrom());
			outgoingMessage.setChatId(message.getChatId());
			postback.addMessage(outgoingMessage);
			NetworkUtils.postJsonMessage(postback);
		}
	}

	/**
	 * Adds the action frame.
	 *
	 * @param event
	 *            the event
	 * @param reply
	 *            the reply
	 */
	protected final void addActionFrame(Event event, Reply<? extends Message> reply) {
		ActionFrameBuilder.getInstance().setEvent(event).addReply(reply).buildToContext();
	}

	/**
	 * Adds the action frame.
	 *
	 * @param event
	 *            the event
	 * @param replies
	 *            the replies
	 */
	protected final void addActionFrame(Event event, List<Reply<? extends Message>> replies) {
		ActionFrameBuilder.getInstance().setEvent(event).addReplies(replies).buildToContext();
	}

	/**
	 * Adds the action frame.
	 *
	 * @param event
	 *            the event
	 * @param replies
	 *            the replies
	 */
	protected final void addActionFrame(Event event, Reply<? extends Message>... replies) {
		ActionFrameBuilder.getInstance().setEvent(event).addReplies(replies).buildToContext();
	}

	/**
	 * Action frame builder.
	 *
	 * @return the action frame builder
	 */
	protected ActionFrameBuilder actionFrameBuilder() {
		return ActionFrameBuilder.getInstance();
	}

	/**
	 * To event.
	 *
	 * @param eventType
	 *            the event type
	 * @param textOrPattern
	 *            the text or pattern
	 * @return the event
	 */
	private Event toEvent(KikBotMillEventType eventType, String textOrPattern) {
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
