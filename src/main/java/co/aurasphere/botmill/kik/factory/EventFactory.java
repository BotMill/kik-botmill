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
package co.aurasphere.botmill.kik.factory;

import co.aurasphere.botmill.kik.incoming.event.AnyEvent;
import co.aurasphere.botmill.kik.incoming.event.DeliveryReceiptEvent;
import co.aurasphere.botmill.kik.incoming.event.FriendPickerEvent;
import co.aurasphere.botmill.kik.incoming.event.IsTypingEvent;
import co.aurasphere.botmill.kik.incoming.event.LinkMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.MentionEvent;
import co.aurasphere.botmill.kik.incoming.event.PictureMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.StartChattingEvent;
import co.aurasphere.botmill.kik.incoming.event.StickerEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessageEvent;
import co.aurasphere.botmill.kik.incoming.event.TextMessagePatternEvent;
import co.aurasphere.botmill.kik.incoming.event.VideoMessageEvent;
import co.aurasphere.botmill.kik.incoming.model.PictureMessage;

/**
 * A factory for creating Event objects.
 * 
 * @author Alvin P. Reyes
 */
public class EventFactory {
	
	/**
	 * Text message pattern.
	 *
	 * @param pattern the pattern
	 * @return the text message pattern event
	 */
	public static TextMessagePatternEvent textMessagePattern(String pattern){
		return new TextMessagePatternEvent().setPattern(pattern);
	}
	
	/**
	 * Text message.
	 *
	 * @param text the text
	 * @return the text message event
	 */
	public static TextMessageEvent textMessage(String text){
		return new TextMessageEvent().setText(text);
	}
	
	/**
	 * Picture.
	 *
	 * @return the picture message event
	 */
	public static PictureMessageEvent picture(){
		return new PictureMessageEvent();
	}
	
	/**
	 * Picture.
	 *
	 * @param incomingPictureMessage the incoming picture message
	 * @return the picture message event
	 */
	public static PictureMessageEvent picture(PictureMessage incomingPictureMessage){
		return new PictureMessageEvent();
	}
	
	/**
	 * Video.
	 *
	 * @return the video message event
	 */
	public static VideoMessageEvent video(){
		return new VideoMessageEvent();
	}
	
	/**
	 * Link.
	 *
	 * @return the link message event
	 */
	public static LinkMessageEvent link() {
		return new LinkMessageEvent();
	}
	
	/**
	 * Checks if is typing.
	 *
	 * @return the checks if is typing event
	 */
	public static IsTypingEvent isTyping() {
		return new IsTypingEvent();
	}
	
	/**
	 * Mention.
	 *
	 * @return the mention event
	 */
	public static MentionEvent mention() {
		return new MentionEvent();
	}
	
	/**
	 * Start chatting.
	 *
	 * @return the start chatting event
	 */
	public static StartChattingEvent startChatting() {
		return new StartChattingEvent();
	}
	
	/**
	 * Sticker event.
	 *
	 * @return the sticker event
	 */
	public static StickerEvent stickerEvent() {
		return new StickerEvent();
	}
	
	/**
	 * Friend picker.
	 *
	 * @return the friend picker event
	 */
	public static FriendPickerEvent friendPicker() {
		return new FriendPickerEvent();
	}
	
	
	/**
	 * Delivery receipt.
	 *
	 * @return the delivery receipt event
	 */
	public static DeliveryReceiptEvent deliveryReceipt() {
		return new DeliveryReceiptEvent();
	}
	
	/**
	 * Any event.
	 *
	 * @return the any event
	 */
	public static AnyEvent anyEvent() {
		return new AnyEvent();
	}
	
}
