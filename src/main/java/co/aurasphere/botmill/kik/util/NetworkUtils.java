/*
 * 
 */
package co.aurasphere.botmill.kik.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.aurasphere.botmill.kik.exception.KikError;
import co.aurasphere.botmill.kik.exception.KikErrorMessage;

/**
 * Class that contains methods that allows KikBotMill to communicate through the
 * network.
 * 
 * @author Alvin Reyes
 * 
 */
public class NetworkUtils {

	/**
	 * The logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(NetworkUtils.class);

	/**
	 * POSTs a message as a JSON string to Facebook.
	 * 
	 * @param input
	 *            the JSON data to send.
	 */
	public static void postJsonMessage(Object input) {
		StringEntity stringEntity = toStringEntity(input);
		postJsonMessage(stringEntity);
	}

	/**
	 * POSTs a thread setting as a JSON string to Facebook.
	 * 
	 * @param input
	 *            the JSON data to send.
	 */
	public static void postThreadSetting(Object input) {
		StringEntity stringEntity = toStringEntity(input);
		postThreadSetting(stringEntity);
	}

	/**
	 * Sends a request.
	 * 
	 * @param request
	 *            the request to send
	 * @return response the response.
	 */
	private static String send(HttpRequestBase request) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		logger.debug(request.getRequestLine().toString());
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(request);
			response = logResponse(httpResponse);
		} catch (Exception e) {
			logger.error("Error during HTTP connection to Facebook: ", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("Error while closing HTTP connection: ", e);
			}
		}
		return response;
	}

	/**
	 * Logs the response before returning it.
	 *
	 * @param response
	 *            the response to log.
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static String logResponse(HttpResponse response) throws IOException {
		int statusCode = response.getStatusLine().getStatusCode();

		// Logs the raw JSON for debug purposes.
		String output = getResponseContent(response);
		logger.debug("HTTP Status Code: {}", statusCode);
		logger.debug("Response: {}", output);

		if (statusCode >= 400) {
			logger.error("HTTP connection failed with error code {}.", statusCode);

			// Parses the error message and logs it.
			KikErrorMessage errorMessage = JsonUtils.fromJson(output, KikErrorMessage.class);
			KikError error = errorMessage.getError();
			logger.error("Error message from Facebook. Message: [{}], Code: [{}], Type: [{}], FbTraceID: [{}].",
					error.getMessage(), error.getCode(), error.getType(), error.getFbTraceId());
		}
		return output;
	}

	/**
	 * Utility method that converts an HttpResponse to a String.
	 *
	 * @param response
	 *            the response to convert.
	 * @return a String with the response content.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static String getResponseContent(HttpResponse response) throws IOException {
		InputStream inputStream = response.getEntity().getContent();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		StringBuilder builder = new StringBuilder();
		String output = null;
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		return builder.toString();
	}

	/**
	 * DELETEs a JSON string to Facebook.
	 * 
	 * @param input
	 *            the JSON data to send.
	 */
	public static void delete(Object input) {
		StringEntity stringEntity = toStringEntity(input);
		delete(stringEntity);
	}

	/**
	 * Validates a Facebook Page Token.
	 * 
	 * @param pageToken
	 *            the token to validate.
	 * @return true if the token is not null or empty, false otherwise.
	 */
	private static boolean validatePageToken(String pageToken) {
		if (pageToken == null || pageToken.isEmpty()) {
			logger.error(
					"FbBotMill validation error: Page token can't be null or empty! Have you called the method FbBotMillContext.getInstance().setup(String, String)?");
			return false;
		}
		return true;
	}

	/**
	 * Utility to send a POST request.
	 * 
	 * @param url
	 *            the url we need to send the post request to.
	 * @param entity
	 *            the entity that containts the object we need to pass as part
	 *            of the post request.
	 * @return {@link String}
	 */
	public static String post(String url, StringEntity entity) {
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setEntity(entity);
		return send(post);
	}

	/**
	 * Utility to send a GET request.
	 * 
	 * @param url
	 *            the url we need to send the get request to.
	 * @return {@link String}
	 */
	public static String get(String url) {
		System.out.println(url);
		HttpGet get = new HttpGet(url);
		return send(get);
	}

	/**
	 * Utility method that converts an object to its StringEntity
	 * representation.
	 * 
	 * @param object
	 *            the object to convert to a StringEntity.
	 * @return a {@link StringEntity} object containing the object JSON.
	 */
	private static StringEntity toStringEntity(Object object) {
		StringEntity input = null;
		try {
			String json = JsonUtils.toJson(object);
			input = new StringEntity(json);
			input.setContentType("application/json");
			logger.debug("Request: {}", inputStreamToString(input.getContent()));
		} catch (Exception e) {
			logger.error("Error during JSON message creation: ", e);
		}
		return input;
	}

	/**
	 * Utility method which converts an InputStream to a String.
	 *
	 * @param stream
	 *            the InputStream to convert.
	 * @return a String with the InputStream content.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static String inputStreamToString(InputStream stream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		String resultString = null;
		while ((length = stream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		resultString = result.toString("UTF-8");
		return resultString;
	}

}
