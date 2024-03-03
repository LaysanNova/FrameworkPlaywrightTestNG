package utils.runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import utils.reports.LoggerUtils;

import java.nio.file.Paths;

public final class BrowserManager {

    public static Browser createBrowser(Playwright playwright) {
        switch (ProjectProperties.BROWSER_TYPE_NAME) {
            case "chromium" -> {
                return playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(ProjectProperties.IS_HEADLESS)
                        .setSlowMo(ProjectProperties.IS_SLOW));
            }
            case "firefox" -> {
                return playwright.firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless(ProjectProperties.IS_HEADLESS)
                        .setSlowMo(ProjectProperties.IS_SLOW));
            }
            case "webkit" -> {
                return playwright.webkit().launch(new BrowserType.LaunchOptions()
                        .setHeadless(ProjectProperties.IS_HEADLESS)
                        .setSlowMo(ProjectProperties.IS_SLOW));
            }
            default -> {
                LoggerUtils.logError("WARNING: Default browser CHROMIUM launched");

                return playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(ProjectProperties.IS_HEADLESS));
            }
        }
    }
    public static BrowserContext createContext(Browser browser) {
        return browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(ProjectProperties.SCREEN_SIZE_WIDTH, ProjectProperties.SCREEN_SIZE_HEIGHT)
                .setRecordVideoDir(Paths.get("target/videos/"))
                .setRecordVideoSize(1280, 720)
        );
    }
}