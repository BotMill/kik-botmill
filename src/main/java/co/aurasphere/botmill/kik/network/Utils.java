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
package co.aurasphere.botmill.kik.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.google.gson.JsonArray;
import co.aurasphere.botmill.kik.model.KikApi;
import co.aurasphere.botmill.kik.model.User;

/**
 * The Class Utils.
 */
public class Utils {

	/** The Constant HMAC_SHA1_ALGORITHM. */
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	/** The ip. */
	private static String IP = "";

	/**
	 * To hex string.
	 *
	 * @param bytes
	 *            the bytes
	 * @return the string
	 */
	private static String toHexString(byte[] bytes) {
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();

		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		return formatter.toString();
	}

	/**
	 * Calculate RFC 2104 HMAC.
	 *
	 * @param data
	 *            the data
	 * @param key
	 *            the key
	 * @return the string
	 * @throws SignatureException
	 *             the signature exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws InvalidKeyException
	 *             the invalid key exception
	 */
	public static String calculateRFC2104HMAC(String data, String key)
			throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);

		return toHexString(mac.doFinal(data.getBytes()));
	}

	/**
	 * Calculate file MD 5.
	 *
	 * @param file
	 *            the file
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String calculateFileMD5(File file) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");

		md.reset();
		InputStream is = new FileInputStream(file);
		byte[] bytes = new byte[2048];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			md.update(bytes, 0, numBytes);
		}
		byte[] digest = md.digest();
		is.close();
		String result = new String(toHexString(digest));
		return result;
	}

	/**
	 * Gets the public IP.
	 *
	 * @return the public IP
	 * @throws Exception
	 *             the exception
	 */
	public static String getPublicIP() throws Exception {
		if (IP.isEmpty())
			IP = getPageAsString(new URL("http://ipinfo.io/ip"), null).trim();

		return IP;
	}

	/**
	 * Gets the page as string.
	 *
	 * @param url
	 *            the url
	 * @param proxy
	 *            the proxy
	 * @return the page as string
	 */
	public static String getPageAsString(URL url, Proxy proxy) {
		String page = "";
		try {
			URLConnection conn = null;

			if (proxy == null) {
				conn = url.openConnection();
			} else {
				conn = url.openConnection(proxy);
			}
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuilder builder = new StringBuilder();
			String currLine;
			while ((currLine = reader.readLine()) != null) {
				builder.append(currLine + "\n");
			}
			reader.close();

			page = builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * Gets the participants.
	 *
	 * @param jsonArray
	 *            the json array
	 * @param kikApi
	 *            the kik api
	 * @return the participants
	 */
	public static ArrayList<User> getParticipants(JsonArray jsonArray, KikApi kikApi) {
		ArrayList<User> participants = new ArrayList<User>();

		for (int i = 0; i < jsonArray.size(); i++) {
			participants.add(new User(jsonArray.get(i).getAsString(), kikApi));
		}

		return participants;
	}
}
