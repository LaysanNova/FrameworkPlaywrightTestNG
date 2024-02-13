package tests.Login;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.reports.ReportUtils;
import utils.runner.ProjectProperties;

import static utils.reports.LoggerUtils.logInfo;

public class LoginUtils {

    public static void login(Page page) {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in")).click();
        page.fill("input[id='loginusername']", ProjectProperties.USERNAME);
        page.fill("input[id='loginpassword']", ProjectProperties.PASSWORD);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sing out")).isVisible();

        page.waitForLoadState();
        page.waitForSelector("#logout2");
        logInfo("Login context: Login successful." + ReportUtils.getEndLine());

    }

    public static void logout(Page page) {

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log out")).click();
        logInfo("Login context: Logout successful." + ReportUtils.getEndLine());
    }

    public static String getUserToken() {

        return ProjectProperties.TOKEN;
    }
}