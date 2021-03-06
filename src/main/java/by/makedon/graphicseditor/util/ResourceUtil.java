package by.makedon.graphicseditor.util;

import java.util.Properties;

/**
 * @author Yahor Makedon
 */
public final class ResourceUtil {
    private static Properties properties;

    static {
        properties = loadApplicationProperties();
    }

    private ResourceUtil() {
    }

    public static String getPropertyValue(String key) {
        String propertyValue = properties.getProperty(key);
        if (propertyValue == null) {
            throw new NullPointerException("Property value should not be null");
        } else {
            return propertyValue;
        }
    }

    @SuppressWarnings("all")
    private static Properties loadApplicationProperties() {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread()
                                  .getContextClassLoader()
                                  .getResourceAsStream(Constants.APPLICATION_PROPERTIES_FILENAME));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return properties;
    }
}
