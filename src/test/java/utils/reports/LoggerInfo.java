package utils.reports;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public final class LoggerInfo {
    public static String getPlaywrightId(Playwright playwright) {
        String[] text = playwright.toString().split("impl.");

        return text[text.length - 1];
    }

    public static String getBrowserInfo(Browser browser) {

        String browserName = browser.browserType().name().toUpperCase();
        String[] browserInfo = browser.toString().split("impl.");
        String browserId = browserInfo[browserInfo.length - 1];

        return "Browser " + browserName + " " + browserId + " launched.\n";
    }

    public static String stepInfo(Object object, String step) {
        return object.getClass().getEnclosingMethod().getName() +
                "\n\t\t\tStep: " + step;
    }

    public static String stepInfo(String step) {
        return "\n\t\t\tStep: " + step;
    }
}
