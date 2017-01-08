/*
 * 
 */
package co.aurasphere.botmill.kik.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * The Class EnumLowercaseSerializer.
 */
public class EnumLowercaseSerializer implements JsonSerializer<Enum<?>> {

	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public JsonElement serialize(Enum<?> src, Type typeOfSrc, JsonSerializationContext context) {
		//lower case and convert "_" into '-';
		String source = src.name().replace('_', '-');
		return context.serialize(source.toLowerCase());
	}
}