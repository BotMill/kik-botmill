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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.aurasphere.botmill.common.BotDefinition;
import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.exception.BotMillMissingConfigurationException;
import co.aurasphere.botmill.kik.exception.KikBotMillException;
import co.aurasphere.botmill.kik.incoming.event.AnyEvent;
import co.aurasphere.botmill.kik.incoming.event.DeliveryReceiptEvent;
import co.aurasphere.botmill.kik.incoming.event.EventType;
import co.aurasphere.botmill.kik.incoming.event.FriendPickerEvent;
import co.aurasphere.botmill.kik.incoming.event.IsTypingEvent;
import co.aurasphere.botmill.kik.incoming.event.LinkMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.MentionEvent;
import co.aurasphere.botmill.kik.incoming.event.PictureMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.ScanDataEvent;
import co.aurasphere.botmill.kik.incoming.event.StartChattingEvent;
import co.aurasphere.botmill.kik.incoming.event.StickerEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessagePatternEvent;
import co.aurasphere.botmill.kik.incoming.event.VideoMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.annotation.BotMillController;
import co.aurasphere.botmill.kik.incoming.event.annotation.BotMillInit;
import co.aurasphere.botmill.kik.util.properties.PropertiesUtil;

/**
 * The Class AbstractDomain.
 * 
 * @author Alvin P. Reyes
 */
public abstract class AbstractKikBot implements BotDefinition {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AbstractKikBot.class);
	private static final String KIK_BOTMILL_PROPERTIES_FILENAME = "botmill.properties";
	private static final String KIK_BOTMILL_USER_NAME_PROP = "kik.user.name";
	private static final String KIK_BOTMILL_API_KEY_PROP = "kik.api.key";
	private static final String KIK_BOTMILL_USER_NAME_PROPERTY = "USERNAME";
	private static final String KIK_BOTMILL_API_KEY_PROPERTY = "API_KEY";
	private static final String KIK_BOTMILL_USER_NAME_PROP_PHOLDER = "<USERNAME>";
	private static final String KIK_BOTMILL_API_KEY_PROP_PHOLDER = "<API_KEY>";

	/** The action frame. */
	private ActionFrame actionFrame;

	/**
	 * Instantiates a new abstract domain.
	 */
	public AbstractKikBot() {
		try {
			this.buildKikBotConfig();
			this.buildAnnotatedInitDomain();
			this.buildAnnotatedDomain();
			this.defineBehaviour();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public void defineBehaviour() {}
	
	/**
	 * Builds the annotated init domain.
	 */
	private void buildAnnotatedInitDomain() {
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(BotMillInit.class)) {
				try {
					method.invoke(this);
				} catch (Exception e) {
					logger.error(e.getMessage());
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
		Properties prop = PropertiesUtil.load(KIK_BOTMILL_PROPERTIES_FILENAME);
		String kikUsername;
		String kikApiKey;
		
		try {
			kikUsername = ((prop.getProperty(KIK_BOTMILL_USER_NAME_PROP).equals("")
					|| prop.getProperty(KIK_BOTMILL_USER_NAME_PROP).indexOf(KIK_BOTMILL_USER_NAME_PROP_PHOLDER) == 0) ? System.getenv(KIK_BOTMILL_USER_NAME_PROPERTY)
							: prop.getProperty(KIK_BOTMILL_USER_NAME_PROP));

			kikApiKey = ((prop.getProperty(KIK_BOTMILL_API_KEY_PROP).equals("")
					|| prop.getProperty(KIK_BOTMILL_API_KEY_PROP).indexOf(KIK_BOTMILL_API_KEY_PROP_PHOLDER) == 0) ? System.getenv(KIK_BOTMILL_API_KEY_PROPERTY)
							: prop.getProperty(KIK_BOTMILL_API_KEY_PROP));
		} catch (Exception e) {
			logger.error("Make sure that kik.user.name and kik.api.key properties exist on the property file");
			return;
		}

		if (kikUsername == null || kikApiKey == null) {
			logger.error("Kik-BotMill Configuration is missing (botmill.properties). "
					+ "Please check if the appropriate property values are configured correctly.");
		}

		// Everything goes well, initialize the setup.
		KikBotMillContext.getInstance().setup(kikUsername, kikApiKey);

	}

	/**
	 * Builds the annotated domain.
	 *
	 * @throws KikBotMillException
	 *             the kik bot mill exception
	 */
	private void buildAnnotatedDomain() throws KikBotMillException {
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(BotMillController.class)) {
				BotMillController botMillController = method.getAnnotation(BotMillController.class);
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
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Reply.
	 *
	 * @param reply
	 *            the reply
	 */
	protected final void reply(Reply<? extends Message> reply) {
		actionFrame.addReply(reply);
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
		}
		return null; // it's impossible to have a null event, but if it does
						// happen, it will be ignored on the handler.
	}
}
