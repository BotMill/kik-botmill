package co.aurasphere.botmill.kik.builder;

import java.util.HashMap;
import java.util.Map;

public class MetadataBuilder {
	
	private static Map<String,String> metadata = new HashMap<String,String>();
	private static MetadataBuilder instance;
	/**
	 * Instantiates a new action frame builder.
	 */
	private MetadataBuilder() {}
	
	/**
	 * Creates the action.
	 *
	 * @return the action frame builder
	 */
	public static MetadataBuilder getInstance() {
		if (instance == null) {
			instance = new MetadataBuilder();
		}
		metadata = new HashMap<String,String>();
		return instance;
	}
	
	public MetadataBuilder addMetadata(String key, String value) {
		metadata.put(key, value);
		return this;
	}
	
	public Map<String,String> build() {
		return metadata;
	}
}
