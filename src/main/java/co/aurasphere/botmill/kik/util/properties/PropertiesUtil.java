package co.aurasphere.botmill.kik.util.properties;

import java.util.Properties;

import co.aurasphere.botmill.kik.exception.BotMillMissingConfigurationException;

public class PropertiesUtil {

	private static Properties properties = new Properties();
	
	public static Properties load(String propertiesPath) throws BotMillMissingConfigurationException {
		try {
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesPath));
		} catch (Exception e) {
			throw new BotMillMissingConfigurationException("Missing configuration file (botmill.properties)");
		}
		return properties;
	}	
}
