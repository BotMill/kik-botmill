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
package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.model.BaseBuilder;
import co.aurasphere.botmill.kik.model.Buildable;
import co.aurasphere.botmill.kik.model.Keyboardable;
import co.aurasphere.botmill.kik.model.MediaAttribution;
import co.aurasphere.botmill.kik.model.MessageType;
import co.aurasphere.botmill.kik.outgoing.model.VideoMessage;

/**
 * The Class VideoMessageBuilder.
 */
public class VideoMessageBuilder extends BaseBuilder
		implements Keyboardable<VideoMessageBuilder>, Buildable<VideoMessage> {
	
	/** The video message. */
	private static VideoMessage videoMessage;
	
	/** The instance. */
	private static VideoMessageBuilder instance;
	
	/** The keyboard builder. */
	private static KeyboardBuilder<VideoMessageBuilder> keyboardBuilder;

	/**
	 * Gets the single instance of VideoMessageBuilder.
	 *
	 * @return single instance of VideoMessageBuilder
	 */
	public static VideoMessageBuilder getInstance() {
		if (instance == null) {
			instance = new VideoMessageBuilder();
		}
		videoMessage = new VideoMessage();
		videoMessage.setType(MessageType.VIDEO);
		return instance;
	}

	/**
	 * Instantiates a new video message builder.
	 */
	public VideoMessageBuilder() {
		videoMessage = new VideoMessage();
		videoMessage.setType(MessageType.VIDEO);
	}

	/**
	 * Sets the video url.
	 *
	 * @param videoUrl the video url
	 * @return the video message builder
	 */
	public VideoMessageBuilder setVideoUrl(String videoUrl) {
		videoMessage.setVideoUrl(videoUrl);
		return this;
	}
	
	/**
	 * Sets the loop.
	 *
	 * @param loop the loop
	 * @return the video message builder
	 */
	public VideoMessageBuilder setLoop(boolean loop) {
		videoMessage.setLoop(loop);
		return this;
	}
	
	/**
	 * Sets the muted.
	 *
	 * @param muted the muted
	 * @return the video message builder
	 */
	public VideoMessageBuilder setMuted(boolean muted) {
		videoMessage.setMuted(muted);
		return this;
	}
	
	/**
	 * Sets the autoplay.
	 *
	 * @param autoplay the autoplay
	 * @return the video message builder
	 */
	public VideoMessageBuilder setAutoplay(boolean autoplay) {
		videoMessage.setAutoplay(autoplay);
		return this;
	}
	
	/**
	 * Sets the no save.
	 *
	 * @param nosave the nosave
	 * @return the video message builder
	 */
	public VideoMessageBuilder setNoSave(boolean nosave) {
		videoMessage.setNoSave(nosave);
		return this;
	}
	
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Keyboardable#addKeyboard()
	 */
	@Override
	public KeyboardBuilder<VideoMessageBuilder> addKeyboard() {
		keyboardBuilder = new KeyboardBuilder<VideoMessageBuilder>(this);
		videoMessage.addKeyboard(keyboardBuilder.buildKeyboard());
		return keyboardBuilder;
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Keyboardable#endKeyboard()
	 */
	@Override
	public VideoMessageBuilder endKeyboard() {
		return (VideoMessageBuilder) keyboardBuilder.getParentBuilder();
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Buildable#build()
	 */
	@Override
	public VideoMessage build() {
		videoMessage.setAttribution(MediaAttribution.CAMERA);
		return videoMessage;
	}
}
