package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.helpers.TestData;
import utils.runner.LoginUtils;
import utils.runner.ProjectProperties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static tests.helpers.TestData.*;

public final class NavigationTest extends BaseTest {

    @Test(testName = "TC.XXX.XX: Verify Landing Page Display")
    @Description("o ensure that the landing page of the web application displays correctly, providing a positive user experience.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLandingURL() {

        assertThat(getPage()).hasURL(BASE_URL + _END_POINT);
        assertThat(getPage()).hasTitle(TITLE);
    }

    @Test(testName = "TC.XXX.XX: Verify Navigation Bar Functionality when customer is logged in.",
            dataProvider = "menuItemsLoggedIn", dataProviderClass = TestData.class)
    @Description("To ensure that the navigation bar in the web application works correctly, allowing users to navigate seamlessly between different sections.")
    @Parameters({"menuName", "expectedEndPoint"})
    @Severity(SeverityLevel.CRITICAL)
    public void testNavigationCustomerLoggedIn(String menuName, String expectedEndPoint) {

        Locator menuButton = getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(menuName));

        getPage().waitForTimeout(2000);

        assertThat(menuButton).isVisible();

        menuButton.click();
        assertThat(getPage()).hasURL(ProjectProperties.BASE_URL + expectedEndPoint);
        assertThat(getPage()).hasTitle(TITLE);
    }

    @Test(
            testName = "TC.XXX.XX: Verify Navigation Bar Functionality when customer is logged out.",
            dataProvider = "menuItemsLoggedOut", dataProviderClass = TestData.class,
            dependsOnMethods = "testNavigationCustomerLoggedIn"
    )
    @Description("To ensure that the navigation bar in the web application works correctly, allowing users to navigate seamlessly between different sections.")
    @Parameters({"menuItemsLoggedOut", "expectedEndPoint"})
    @Severity(SeverityLevel.CRITICAL)
    public void testNavigationCustomerLoggedOut(String menuName, String expectedEndPoint) {

        LoginUtils.logout(getPage());

        Locator menuButton = getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(menuName));

        getPage().waitForTimeout(2000);
        assertThat(menuButton).isVisible();

        menuButton.click();
        assertThat(getPage()).hasURL(ProjectProperties.BASE_URL + expectedEndPoint);
        assertThat(getPage()).hasTitle(TITLE);
    }
}




