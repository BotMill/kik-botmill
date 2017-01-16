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
package co.aurasphere.botmill.kik.outgoing.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import co.aurasphere.botmill.kik.configuration.Keyboard;
import co.aurasphere.botmill.kik.model.Attribution;
import co.aurasphere.botmill.kik.model.MediaAttribution;

/**
 * The Class VideoMessage.
 * 
 * @author Alvin P. Reyes
 */
public class VideoMessage extends OutgoingMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The video url. */
	private String videoUrl;
	
	/** The loop. */
	private boolean loop;
	
	/** The muted. */
	private boolean muted;
	
	/** The autoplay. */
	private boolean autoplay;
	
	/** The no save. */
	private boolean noSave;
	
	/** The attribution. */
	private MediaAttribution attribution;
	
	/** The keyboard. */
	@SerializedName("keyboards")
	private List<Keyboard> keyboards  = new ArrayList<Keyboard>();

	/**
	 * Gets the video url.
	 *
	 * @return the video url
	 */
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * Sets the video url.
	 *
	 * @param videoUrl the new video url
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	/**
	 * Checks if is loop.
	 *
	 * @return true, if is loop
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * Sets the loop.
	 *
	 * @param loop the new loop
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/**
	 * Checks if is muted.
	 *
	 * @return true, if is muted
	 */
	public boolean isMuted() {
		return muted;
	}

	/**
	 * Sets the muted.
	 *
	 * @param muted the new muted
	 */
	public void setMuted(boolean muted) {
		this.muted = muted;
	}

	/**
	 * Checks if is autoplay.
	 *
	 * @return true, if is autoplay
	 */
	public boolean isAutoplay() {
		return autoplay;
	}

	/**
	 * Sets the autoplay.
	 *
	 * @param autoplay the new autoplay
	 */
	public void setAutoplay(boolean autoplay) {
		this.autoplay = autoplay;
	}

	/**
	 * Checks if is no save.
	 *
	 * @return true, if is no save
	 */
	public boolean isNoSave() {
		return noSave;
	}

	/**
	 * Sets the no save.
	 *
	 * @param noSave the new no save
	 */
	public void setNoSave(boolean noSave) {
		this.noSave = noSave;
	}
	
	/**
	 * Adds the keyboard.
	 *
	 * @param keyboard the keyboard
	 */
	public void addKeyboard(Keyboard keyboard) {
		this.keyboards.add(keyboard);
	}

	/**
	 * Gets the keyboards.
	 *
	 * @return the keyboards
	 */
	public List<Keyboard> getKeyboards() {
		return keyboards;
	}

	/**
	 * Sets the keyboards.
	 *
	 * @param keyboards the new keyboards
	 */
	public void setKeyboards(List<Keyboard> keyboards) {
		this.keyboards = keyboards;
	}

	/**
	 * Gets the attribution.
	 *
	 * @return the attribution
	 */
	public MediaAttribution getAttribution() {
		return attribution;
	}

	/**
	 * Sets the attribution.
	 *
	 * @param attribution the new attribution
	 */
	public void setAttribution(MediaAttribution attribution) {
		this.attribution = attribution;
	}
}
