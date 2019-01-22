package util;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * This is a utility for reading properties from config.properties file
 */
public class ReadPropertiesFile {
    private static final String propertiesFilename = "file/config.properties";

    public static String readKey(String key) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(propertiesFilename)) {
            props.load(resourceStream);
        }
        return props.getProperty(key);

    }
}
