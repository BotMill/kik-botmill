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
package co.aurasphere.botmill.kik.incoming.model;


import co.aurasphere.botmill.kik.model.Attribution;

/**
 * The Class VideoMessage.
 * 
 * @author Alvin P. Reyes
 */
public class VideoMessage extends IncomingMessage implements Comparable<VideoMessage> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The video url. */
	private String videoUrl;
	
	/** The attribution. */
	private Attribution attribution;
	
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
	 * Gets the attribution.
	 *
	 * @return the attribution
	 */
	public Attribution getAttribution() {
		return attribution;
	}
	
	/**
	 * Sets the attribution.
	 *
	 * @param attribution the new attribution
	 */
	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(VideoMessage o) {
		if(this.getVideoUrl().equals(o.getVideoUrl())) {
			return 0;
		}
		return -1;
	}

}
