/*
 * 
 */
package co.aurasphere.botmill.kik.util;

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
		return context.serialize(src.name().toLowerCase());
	}
}