package co.aurasphere.botmill.kik.json;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LowerCaseTypeAdapterFactory implements TypeAdapterFactory {

	@Override
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
						//System.out.println(entry.getValue());
						//System.out.println(entry.getKey());
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
