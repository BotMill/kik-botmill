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

import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.builder.ActionFrameBuilder;
import co.aurasphere.botmill.kik.builder.LinkMessageBuilder;
import co.aurasphere.botmill.kik.builder.PictureMessageBuilder;
import co.aurasphere.botmill.kik.builder.VideoMessageBuilder;
import co.aurasphere.botmill.kik.factory.EventFactory;
import co.aurasphere.botmill.kik.factory.ReplyFactory;
import co.aurasphere.botmill.kik.incoming.handler.model.JsonAction;
import co.aurasphere.botmill.kik.incoming.handler.model.JsonReply;
import co.aurasphere.botmill.kik.incoming.handler.model.JsonToActionFrame;
import co.aurasphere.botmill.kik.model.Frame;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.Reply;
import co.aurasphere.botmill.kik.outgoing.model.LinkMessage;
import co.aurasphere.botmill.kik.outgoing.model.PictureMessage;
import co.aurasphere.botmill.kik.outgoing.model.VideoMessage;
import co.aurasphere.botmill.kik.outgoing.reply.LinkMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.PictureMessageReply;
import co.aurasphere.botmill.kik.outgoing.reply.VideoMessageReply;
import co.aurasphere.botmill.kik.util.json.JsonUtils;
import co.aurasphere.botmill.kik.util.network.NetworkUtils;

/**
 * The Class JsonToActionFrameHandler.
 * 
 * This class is use to handle the JSON Data from an external source (with a conditional format)
 * that in turn converts it to a Kik-BotMill Action Frame. 
 * 
 * The way it works is, when the developer specify a JSON reply (format below). The handler uses 
 * that information to create an Action Frame which can then be used by the Kik ChatBot.
 * 
 * 
 * @author Alvin P. Reyes
 */
public class JsonToActionFrameHandler {

	/** The Constant CONST_TEXT. */
	private static final String CONST_TEXT = "text";

	/** The Constant CONST_PATTERN. */
	private static final String CONST_PATTERN = "pattern";

	/**
	 * This method is called to convert the JSON Response to an actual Kik-BotMill Action Frame.
	 *
	 * @param jsonUrl
	 *            the json url. 
	 *            
	 * JSON Format of the source should follow the following convention.
	 * 
	 * {
	 *	"jsonkikbotmill": [
	 *			{
	 *				"event":"pattern",
	 *				"input":"(?i:who (wrote|created|create) (this|you|)[\\?])",
	 *				"replies":[
	 *						{"type":"text","text":{"body":"Written by the brilliant minds of Alvin Reyes and Donato Rimenti. Bad-ass Java Developers."}},
	 *						{"type":"text","text":{"body":"Written by the brilliant minds of Alvin Reyes and Donato Rimenti. Bad-ass Java Developers."}},
	 *						{"type":"picture", "picture":{"text":"picture","title":"title picture","url":""}},
	 *						{"type":"link","link": {"text":"link","title":"link title","url":""}},
	 *						{"type":"video", "video": {"text":"video","title":"title video","url":""}},
	 *				]
	 *			}
	 *		]
	 *	}
	 * @return the list
	 */
	public static List<Frame> jsonFrameToActionFrame(String jsonUrl) {

		List<Frame> list = new ArrayList<Frame>();
		String json = NetworkUtils.get(jsonUrl);
		JsonToActionFrame a = JsonUtils.fromJson(json, JsonToActionFrame.class);

		for (JsonAction jaction : a.getJsonTextAction()) {

			if (jaction.getEvent().equals(CONST_PATTERN)) {
				list.add(ActionFrameBuilder.getInstance().setEvent(EventFactory.textMessagePattern(jaction.getInput()))
						.addReplies(processReplies(jaction)).build());
			}else if (jaction.getEvent().equals(CONST_TEXT)) {
				list.add(ActionFrameBuilder.getInstance().setEvent(EventFactory.textMessage(jaction.getInput()))
						.addReplies(processReplies(jaction)).build());
			}
		}

		return list;
	}
	
	/**
	 * This method is used to create replies based off on {@link JsonAction} object.
	 *
	 * @param jaction the jaction
	 * @return the list< reply<? extends message>>
	 */
	private static List<Reply<? extends Message>> processReplies(JsonAction jaction) {
		
		List<Reply<? extends Message>> replies = new ArrayList<Reply<? extends Message>>();
		
		for(final JsonReply jsonReply:jaction.getReplies()) {
			if(jsonReply.getType().equals("text")) {
				replies.add(ReplyFactory.buildTextMessageReply(jsonReply.getText().getBody()));
			}
			
			if(jsonReply.getType().equals("picture")) {
				replies.add(new PictureMessageReply() {
				
					public PictureMessage processReply(Message message) {
						return PictureMessageBuilder.getInstance()
								.setPicUrl(jsonReply.getPicture().getPicUrl())
								.build();
					}
				});
			}
			if(jsonReply.getType().equals("video")) {
				replies.add(new VideoMessageReply() {
			
					public VideoMessage processReply(Message message) {
						return VideoMessageBuilder.getInstance()
								.setVideoUrl(jsonReply.getVideo().getVideoUrl())
								.build();
					}
				});
			}
			if(jsonReply.getType().equals("link")) {
				replies.add(new LinkMessageReply() {
					
		
					public LinkMessage processReply(Message message) {
						return LinkMessageBuilder.getInstance().setText(jsonReply.getLink().getText())
								.setTitle(jsonReply.getLink().getTitle())
								.setUrl(jsonReply.getLink().getUrl())
								.build();
					}
				});
			}
		}
		
		return replies;
	}

}
