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

import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.MessageType;


/**
 * Utility class for handling JSON serialization and deserialization.
 * 
 * @author Alvin Reyes
 * 
 */
public class JsonUtils {

	/**
	 * Gson which handles the JSON conversion.
	 */
	private static Gson gson;

	/**
	 * Initializes the current Gson object if null and returns it. The Gson
	 * object has custom adapters to manage datatypes according to Facebook
	 * formats.
	 * 
	 * @return the current instance of Gson.
	 */
	public static Gson getGson() {
		if (gson == null) {
			// Creates the Gson object which will manage the information
			// received
			GsonBuilder builder = new GsonBuilder();
			// Serializes enums as lower-case.
			builder.registerTypeHierarchyAdapter(Enum.class, new EnumLowercaseSerializer());
			//	EnumDeserializer
			builder.registerTypeHierarchyAdapter(Calendar.class, new CalendarSerializer());
			
			gson = builder.create();
		}
		return gson;
	}

	/**
	 * From json.
	 *
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            the string from which the object is to be deserialized.
	 * @param T
	 *            the type of the desired object.
	 * @return an object of type T from the string. Returns null if json is
	 *         null.
	 * @see Gson#fromJson(String, Class)
	 */
	public static <T> T fromJson(String json, Class<T> T) {
		return getGson().fromJson(json, T);
	}

	/**
	 * To json.
	 *
	 * @param src
	 *            the object for which Json representation is to be created
	 *            setting for Gson .
	 * @return Json representation of src.
	 * @see Gson#toJson(Object)
	 */
	public static String toJson(Object src) {
		return getGson().toJson(src);
	}
	

	public static MessageType getType(String json) {
		Message message = getGson().fromJson(json, Message.class);
		return message.getType();
		
	}

}
