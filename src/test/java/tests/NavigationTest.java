package tests;

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

    @Test()
    @Description("Verify Landing Page Display: Confirm that the application displays the expected landing page associated with the main URL.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLandingURL() {

        assertThat(getPage()).hasURL(BASE_URL + _END_POINT);
        assertThat(getPage()).hasTitle(TITLE);
    }

    @Test(dataProvider = "menuItems", dataProviderClass = TestData.class)
    @Description("Confirm Menu Visibility: Ensure that navigation bars, are visible and accessible during the navigation process.")
    @Parameters({"menuName", "expectedEndPoint"})
    @Severity(SeverityLevel.CRITICAL)
    public void testNavigation(String menuName, String expectedEndPoint) {

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(menuName)).click();
        assertThat(getPage()).hasURL(ProjectProperties.BASE_URL + expectedEndPoint);
        assertThat(getPage()).hasTitle(TITLE);
    }

    @Test
    @Description("Verify Session Termination: Confirm that the user's session is properly terminated.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogOutNavigate() {

        LoginUtils.login(getPage());

        Allure.step("Assertion: Verify Successful Logout.");
        assertThat(getPage()).hasURL(BASE_URL + _END_POINT);
    }
}




