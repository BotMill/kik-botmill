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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.aurasphere.botmill.kik.incoming.handler.IncomingToOutgoingMessageHandler;
import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageCallback;

/**
 * The Class KikBotServlet.
 */
public class KikBotMillServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(KikBotMillServlet.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init() throws ServletException {

		// Tries to get the botDefinitionClass name from the config defined in
		// web.xml.
		String botDefinitionClass = getServletConfig().getInitParameter("bot-definition-class");
		if (botDefinitionClass == null || botDefinitionClass.isEmpty()) {
			logger.error(
					"Bot definition class not found in web.xml. Make sure to add the fully qualified name of the a class implementing co.aurasphere.botmill.fb.KikBotDefinition as an init-param of the FbBotMillServlet.");
			throw new ServletException(
					"Bot definition class not found in web.xml. Make sure to add the fully qualified name of the a class implementing co.aurasphere.botmill.fb.KikBotDefinition as an init-param of the FbBotMillServlet.");
		}

		// Tries to load and instantiate the botDefinitionClass.
		KikBotMillEntry botEntry = null;
		try {
			botEntry = (KikBotMillEntry) this.getClass().getClassLoader().loadClass(botDefinitionClass).newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("Error while loading KikBotDefinition class [ " + botDefinitionClass + " ]", e);
			throw new ServletException("Error while loading KikBotDefinition class [ " + botDefinitionClass + " ]", e);
		} catch (ClassCastException e) {
			logger.error("Class [ " + botDefinitionClass
					+ " ] does not implement co.aurasphere.botmill.kik.KikBotDefinition", e);
			throw new ServletException("Class [ " + botDefinitionClass
					+ " ] does not implement co.aurasphere.botmill.kik.KikBotDefinition", e);
		} catch (Exception e) {
			logger.error("Error during instantiation of class [ " + botDefinitionClass + " ]", e);
			throw new ServletException("Error during instantiation of class [ " + botDefinitionClass + " ]", e);
		}

		botEntry.setWebHookUrl("https://kik-bot-021415.herokuapp.com/kikbot");
		botEntry.kikBotEntry();
		logger.info("KikBotMillServlet configuration OK.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String json = readerToString(req.getReader());
			logger.debug("JSON INPUT: " + json);
			MessageCallback messages = JsonUtils.fromJson(json, MessageCallback.class);

			// Process each message.
			for (Message message : messages.getMessages()) {
				IncomingToOutgoingMessageHandler.createHandler().process(message);
			}
		
		} catch (Exception e) {
			logger.error("Error during MessengerCallback parsing: ", e);
			return;
		} finally {
			resp.setStatus(HttpServletResponse.SC_OK);
		}
	}

	/**
	 * Utility method that converts a Reader to a String.
	 *
	 * @param reader
	 *            the reader to convert.
	 * @return a String with the content of the reader.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KikBotMillServlet []";
	}

}
