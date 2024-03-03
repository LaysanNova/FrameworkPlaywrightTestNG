package utils.runner;

import utils.reports.LoggerUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ProjectProperties {
    private static final String ENV_WEB_OPTIONS = "WEB_OPTIONS";
    private static final String ENV_BROWSER_OPTIONS = "BROWSER_OPTIONS";
    private static Properties properties = init_properties();
    public static final String BROWSER_TYPE_NAME = properties.getProperty("browserType");
    public static final String BASE_URL = properties.getProperty("baseUrl");
    public static final boolean IS_HEADLESS = Boolean.parseBoolean(properties.getProperty("headless"));
    public static final double IS_SLOW = Double.parseDouble(properties.getProperty("slowMo"));
    public static final int SCREEN_SIZE_WIDTH = Integer.parseInt(properties.getProperty("width"));
    public static final int SCREEN_SIZE_HEIGHT = Integer.parseInt(properties.getProperty("height"));
    public static final boolean TRACING_MODE = Boolean.parseBoolean(properties.getProperty("tracing"));
    public static final boolean VIDEO_MODE = Boolean.parseBoolean(properties.getProperty("video"));
    public static final String API_BASE_URL = properties.getProperty("apiBaseUrl");
    public static final String USERNAME = properties.getProperty("username");
    public static final String PASSWORD = properties.getProperty("password");
    public static final String E_PASSWORD = properties.getProperty("ePassword");
    public static final String TOKEN = properties.getProperty("token");

    private static Properties init_properties() {
        if (properties == null) {
            properties = new Properties();
            if (isServerRun()) {
                if (System.getenv(ENV_BROWSER_OPTIONS) != null) {
                    for (String option : System.getenv(ENV_BROWSER_OPTIONS).split(";")) {
                        String[] browserOptionArr = option.split("=");
                        properties.setProperty(browserOptionArr[0], browserOptionArr[1]);
                    }
                }
                if (System.getenv(ENV_WEB_OPTIONS) != null) {
                    for (String option : System.getenv(ENV_WEB_OPTIONS).split(";")) {
                        String[] webOptionArr = option.split("=");
                        properties.setProperty(webOptionArr[0], webOptionArr[1]);
                    }
                }
            } else {
                try {
                    FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config.properties");
                    properties.load(fileInputStream);
                } catch (IOException e) {
                    LoggerUtils.logFatal("FATAL: Properties file NOT found.");
                    System.exit(1);
                }
            }
        }

        return properties;
    }

    public static boolean isServerRun() {

        return System.getenv("CI_RUN") != null;
    }
}