/*
 * 
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
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
package co.aurasphere.botmill.kik.util.json;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * A factory for creating LowerCaseTypeAdapter objects.
 * 
 * @author Alvin P. Reyes
 */
public class LowerCaseTypeAdapterFactory implements TypeAdapterFactory {

	/* (non-Javadoc)
	 * @see com.google.gson.TypeAdapterFactory#create(com.google.gson.Gson, com.google.gson.reflect.TypeToken)
	 */
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
		final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

		return new TypeAdapter<T>() {

			@Override
			public T read(JsonReader in) throws IOException {
                JsonElement tree = elementAdapter.read(in);
                afterRead(tree);
                return delegate.fromJsonTree(tree);
			}

			@Override
			public void write(JsonWriter out, T value) throws IOException {
				JsonElement tree = delegate.toJsonTree(value);
                beforeWrite(value, tree);
                elementAdapter.write(out, tree);
			}
			protected void beforeWrite(T source, JsonElement toSerialize) {}
			protected void afterRead(JsonElement deserialized) {
				if (deserialized instanceof JsonObject) {
					JsonObject jsonObject = ((JsonObject) deserialized);
					Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
					for (Map.Entry<String, JsonElement> entry : entrySet) {
						if (entry.getValue() instanceof JsonElement) {
							if (entry.getKey().equalsIgnoreCase("type")) {
								String val = jsonObject.get(entry.getKey()).toString();
								jsonObject.addProperty(entry.getKey(), val.toLowerCase());
							}
						} else {
							afterRead(entry.getValue());
						}
					}
				}
			}
		};
	}

}
