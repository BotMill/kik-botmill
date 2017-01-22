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
package co.aurasphere.botmill.kik;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;

/**
 * The Class KikBotMillLoader.
 * 
 * Wrapper class that can be used to load the entry point and catch all post request
 * from Kik.
 * 
 * @author Alvin P. Reyes
 */
public class KikBotMillLoader {

	/** The instance. */
	private static KikBotMillLoader instance;
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(KikBotMillLoader.class);

	/**
	 * Gets the loader.
	 *
	 * @return the loader
	 */
	public static KikBotMillLoader getLoader() {
		if (instance == null) {
			instance = new KikBotMillLoader();
		}
		return instance;
	}

	/**
	 * Load entry point.
	 *
	 * @param entry the entry
	 * @return the kik bot mill loader
	 */
	public KikBotMillLoader loadEntryPoint(KikBotMillEntry entry) {
		entry.kikBotEntry();
		return this;
	}
	
	/**
	 * Post All Broadcast defined.
	 *
	 * @param req the req
	 * @param resp the resp
	 */
	public void postBroadcast(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String json = readerToString(req.getReader());
			logger.debug("BROADCAST - JSON INPUT: " + json);
			MessageCallback messages = JsonUtils.fromJson(json, MessageCallback.class);

			for (Message message : messages.getMessages()) {
				IncomingToOutgoingMessageHandler.createHandler().processBroadcast(message);
			}

		} catch (Exception e) {
			logger.error("Error during MessengerCallback parsing: ", e);
			return;
		} finally {
			resp.setStatus(HttpServletResponse.SC_OK);
		}
	}
	
	/**
	 * Post handler.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void postHandler(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String json = readerToString(req.getReader());
			logger.debug("JSON INPUT: " + json);
			MessageCallback messages = JsonUtils.fromJson(json, MessageCallback.class);

			// Process each message.
			for (Message message : messages.getMessages()) {
				// Process the messages.
				IncomingToOutgoingMessageHandler.createHandler().process(message);

				// and process any broadcast
				IncomingToOutgoingMessageHandler.createHandler().processBroadcast(message);
			}

		} catch (Exception e) {
			logger.error("Error during MessengerCallback parsing: ", e);
			return;
		} finally {
			resp.setStatus(HttpServletResponse.SC_OK);
		}
	}
	
	/**
	 * Reader to string.
	 *
	 * @param reader the reader
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String readerToString(Reader reader) throws IOException {
		char[] arr = new char[8 * 1024];
		StringBuilder buffer = new StringBuilder();
		int numCharsRead;
		while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
			buffer.append(arr, 0, numCharsRead);
		}
		return buffer.toString();
	}
}
