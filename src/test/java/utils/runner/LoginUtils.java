package utils.runner;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static utils.reports.LoggerUtils.logInfo;

public class LoginUtils {

    public static void login(Page page) {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in")).click();
        page.fill("input[id='loginusername']", ProjectProperties.USERNAME);
        page.fill("input[id='loginpassword']", ProjectProperties.PASSWORD);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sing out")).isVisible();
        logInfo("Page context: Base URL opened.");
    }

    public static String getUserToken() {

        return ProjectProperties.TOKEN;
    }
}
