package tests.Login;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginModal;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseLoginTest {

    @Test(dataProvider = "loginParameters", dataProviderClass = LoginData.class)
    @Description("Test Login Functionality: Including valid and invalid credentials")
    @Parameters({"menuName", "expectedEndPoint"})
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin(boolean successful, String userName, String password, String message) {

        HomePage homePage = new HomePage(getPage());

        final LoginModal loginModal = homePage.clickLoginMenu();
        final Locator form = loginModal.getDialog();

        Allure.step("Assertion: 'Log in' Form is visible.");
        assertThat(form).isVisible();

        loginModal
                .inputUserName(userName)
                .inputPassword(password)
                .verifyAlertMessage(message)
                .clickLoginButton();

        final Locator welcome = new HomePage(getPage()).getWelcomeMessage();
        final Locator logOut = new HomePage(getPage()).getLogOutItem();
        final Locator login = new HomePage(getPage()).getLoginItem();

        if (successful) {
            assertThat(welcome).isVisible();
            assertThat(logOut).isVisible();
            assertThat(login).not().isVisible();
        } else {
            assertThat(welcome).not().isVisible();
            assertThat(logOut).not().isVisible();
            assertThat(login).isVisible();
        }
    }

    @Test()
    @Description("Test Login Functionality: API test")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginAPI() {

        utils.api.APIUtils.login();
    }
}