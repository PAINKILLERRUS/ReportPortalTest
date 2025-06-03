package configuration;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
