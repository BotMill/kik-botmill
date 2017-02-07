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
package co.aurasphere.botmill.kik.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class MetadataBuilder.
 */
public class MetadataBuilder {
	
	/** The metadata. */
	private static Map<String,String> metadata = new HashMap<String,String>();
	
	/** The instance. */
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
	
	/**
	 * Adds the metadata.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the metadata builder
	 */
	public MetadataBuilder addMetadata(String key, String value) {
		metadata.put(key, value);
		return this;
	}
	
	/**
	 * Builds the.
	 *
	 * @return the map
	 */
	public Map<String,String> build() {
		return metadata;
	}
}
