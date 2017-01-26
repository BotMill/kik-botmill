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
import java.util.HashMap;
import java.util.Map;
import co.aurasphere.botmill.kik.KikBotMillContext;
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

/**
 * The Class AbstractDomain.
 * 
 * @author Alvin P. Reyes
 */
public abstract class AbstractAnnotationDomain implements Domain {

	/** The method map. */
	Map<String,Reply<? extends Message>> methodMap = new HashMap<String,Reply<? extends Message>>();
	
	private ActionFrame actionFrame;

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.model.Domain#buildDomain()
	 */
	@Override
	public void buildDomain() {}
	
	/**
	 * Instantiates a new abstract domain.
	 */
	public AbstractAnnotationDomain() {
		this.buildAnnotatedDomain();
	}
	
	/**
	 * Builds the annotated domain.
	 */
	public void buildAnnotatedDomain() {
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(BotMillController.class)) {
				BotMillController botMillController = method.getAnnotation(BotMillController.class);
				try {
					actionFrame = new ActionFrame();
					String textOrPattern = "";
					if(!botMillController.text().equals("")) {
						textOrPattern = botMillController.text();
					}else {
						textOrPattern = botMillController.pattern();
					}
					actionFrame.setEvent(toEvent(botMillController.event(),textOrPattern));
					method.invoke(this);
					KikBotMillContext.getInstance().addActionFrameToContext(actionFrame);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void reply(Reply<? extends Message> reply) {
		actionFrame.addReply(reply);
	}
	
	/**
	 * To event.
	 *
	 * @param eventType the event type
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
		return null;
	}
}
