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

import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.builder.ActionMessageBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.builder.PictureMessageBuilder;
import co.aurasphere.botmill.kik.builder.TextMessageBuilder;
import co.aurasphere.botmill.kik.builder.VideoMessageBuilder;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.outgoing.model.IsTypingMessage;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;
import co.aurasphere.botmill.kik.outgoing.model.TextMessage;
import co.aurasphere.botmill.kik.outgoing.model.VideoMessage;
import co.aurasphere.botmill.kik.reply.IsTypingReply;
import co.aurasphere.botmill.kik.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.reply.TextMessageReply;
import co.aurasphere.botmill.kik.reply.VideoMessageReply;

/**
 * A factory for creating Reply objects.
 */
public class ReplyFactory {
	
	/**
	 * Builds the text message reply.
	 *
	 * @param text the text
	 * @return the text message reply
	 */
	public static TextMessageReply buildTextMessageReply(String text){
		return new TextMessageReply() {
			@Override
			public TextMessage processReply(Message message) {
				return TextMessageBuilder.getInstance().setBody(text).build();
			}
		};
	}
	
	/**
	 * Builds the picture message reply.
	 *
	 * @param pictureUrl the picture url
	 * @return the picture message reply
	 */
	public static PictureMessageReply buildPictureMessageReply(String pictureUrl) {
		return new PictureMessageReply() {
			@Override
			public PictureMessage processReply(Message message) {
				return PictureMessageBuilder.getInstance().setPicUrl(pictureUrl).build();
			}
		};
	}
	
	/**
	 * Builds the typing reply.
	 *
	 * @return the checks if is typing reply
	 */
	public static IsTypingReply buildTypingReply() {
		return new IsTypingReply() {
			@Override
			public IsTypingMessage processReply(Message message) {
				return ActionMessageBuilder.buildIsTypingMessage();
			}
		};
	}
	
	/**
	 * Builds the video message reply.
	 *
	 * @param videoUrl the video url
	 * @return the video message reply
	 */
	public static VideoMessageReply buildVideoMessageReply(String videoUrl) {
		return new VideoMessageReply() {
			@Override
			public VideoMessage processReply(Message message) {
				return VideoMessageBuilder.getInstance().setVideoUrl(videoUrl).build();
			}
		};
	}
	
	/**
	 * Builds the link message reply.
	 *
	 * @param title the title
	 * @param link the link
	 * @param pictureUrl the picture url
	 * @return the link message reply
	 */
	public static LinkMessageReply buildLinkMessageReply(String title, String link, String pictureUrl) {
		return new LinkMessageReply() {
			@Override
			public LinkMessage processReply(Message message) {
				return LinkMessageBuilder.getInstance().setTitle(title).setUrl(link).setPicUrl(pictureUrl).build();
			}
		};
	}
}
