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
package co.aurasphere.botmill.kik.json;

import java.lang.reflect.Type;

import co.aurasphere.botmill.kik.incoming.model.FriendPickerMessage;
import co.aurasphere.botmill.kik.incoming.model.IsTypingMessage;
import co.aurasphere.botmill.kik.incoming.model.LinkMessage;
import co.aurasphere.botmill.kik.incoming.model.PictureMessage;
import co.aurasphere.botmill.kik.incoming.model.ScanDataMessage;
import co.aurasphere.botmill.kik.incoming.model.StickerMessage;
import co.aurasphere.botmill.kik.incoming.model.TextMessage;
import co.aurasphere.botmill.kik.incoming.model.VideoMessage;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;
import co.aurasphere.botmill.kik.model.MessageType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


/**
 * The Class IncomingMessagesDeserializer.
 */
public class IncomingMessagesDeserializer implements JsonDeserializer<MessageCallback> {

	/**
	 * Gson delegate used to avoid infinite loops during the deserialization.
	 */
	private static Gson delegateGson;

	/**
	 * Instantiates a new AttachmentDeserializer.
	 */
	public IncomingMessagesDeserializer() {
		GsonBuilder builder = new GsonBuilder();
		builder.addDeserializationExclusionStrategy(new SkipDeserializationAnnotationExclusionStrategy());
		delegateGson = builder.create();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 * java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@SuppressWarnings("incomplete-switch")
	public MessageCallback deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		MessageCallback messageCallback = new MessageCallback();
		//	The message callback can have multiple message bodies.
		JsonElement jsonMessages = json.getAsJsonObject().get("messages");
		
		for(int i=0;i<jsonMessages.getAsJsonArray().size();i++) {
			//	get type.
			JsonObject jsonMessage = jsonMessages.getAsJsonArray().get(i).getAsJsonObject();
			String typeString = jsonMessage.get("type").getAsString();
			MessageType messageType = MessageType.valueOf(typeString.replace('-', '_').toUpperCase());
			Class<? extends Message> messageClass = null;
			switch(messageType) {
			case TEXT:
				messageClass = TextMessage.class;
				break;
			case PICTURE:
				messageClass = PictureMessage.class;
				break;
			case VIDEO:
				messageClass = VideoMessage.class;
				break;
			case LINK:
				messageClass = LinkMessage.class;
				break;
			case SCAN_DATA:
				messageClass = ScanDataMessage.class;
				break;
			case STICKER:
				messageClass = StickerMessage.class;
				break;
			case FRIEND_PICKER:
				messageClass = FriendPickerMessage.class;
				break;
			case IS_TYPING:
				messageClass = IsTypingMessage.class;
			}

			Message message = context.deserialize(jsonMessage, messageClass);
			messageCallback.addMessage(message);
		}
		
		return messageCallback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IncomingMessagesDeserializer []";
	}

}
