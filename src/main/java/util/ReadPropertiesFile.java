package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * This is a utility for reading properties from config.properties file
 */
public class ReadPropertiesFile {
    private static final String propertiesFilename = "file/config.properties";

    public static String readKey(String key) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(propertiesFilename)) {
            props.load(resourceStream);
        }
        return props.getProperty(key);

    }
}
