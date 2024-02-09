package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.IRandom;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomeTest extends BaseTest implements IRandom {

    @Test(testName = "TC.XXX.XX: Verify Logout Functionality")
    @Description("Objective: To ensure that the logout feature functions correctly and securely terminates the user session.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogOut() {

        new HomePage(getPage())
                .clickLogOut();

        final Locator welcome = new HomePage(getPage()).getWelcomeMessage();
        final Locator logOut = new HomePage(getPage()).getLogOutItem();
        final Locator login = new HomePage(getPage()).getLoginItem();

        Allure.step("Assertion: 'Welcome' is NOT visible.");
        assertThat(welcome).not().isVisible();

        Allure.step("Assertion: Menu option 'Log out' is NOT visible.");
        assertThat(logOut).not().isVisible();

        Allure.step("Assertion: Menu option 'Log in' is visible.");
        assertThat(login).isVisible();
    }
}
