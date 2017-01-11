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
package co.aurasphere.botmill.kik.webhook;

import java.io.File;
import java.net.ServerSocket;
import java.util.HashMap;

import co.aurasphere.botmill.kik.model.KikApi;

/**
 * The listener interface for receiving threadWebhook events. The class that is
 * interested in processing a threadWebhook event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addThreadWebhookListener</code> method. When the
 * threadWebhook event occurs, that object's appropriate method is invoked.
 *
 * @see ThreadWebhookEvent
 */
public class ThreadWebhookListener extends Thread {

	/** The kik api. */
	private KikApi kikApi;

	/** The file map. */
	private HashMap<String, File> fileMap = new HashMap<String, File>();

	/**
	 * Instantiates a new thread webhook listener.
	 *
	 * @param kikApi
	 *            the kik api
	 */
	public ThreadWebhookListener(KikApi kikApi) {
		this.kikApi = kikApi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(kikApi.getPort());
			ss.setReuseAddress(true);
			System.out.println("Starting webhook listener on port " + kikApi.getPort());

			while (ss.isBound()) {
				new ThreadConnection(ss.accept(), kikApi).start();
			}

			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to start webhook server!");
		}
	}

	/**
	 * Gets the file map.
	 *
	 * @return the file map
	 */
	public HashMap<String, File> getFileMap() {
		return fileMap;
	}
}
