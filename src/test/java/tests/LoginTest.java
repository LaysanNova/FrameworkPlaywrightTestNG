package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginModal;
import tests.Login.LoginData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends LoginBaseTest {

    @Test(
            testName = "TC.XXX.XX: Verify Login Functionality",
            dataProvider = "loginParameters", dataProviderClass = LoginData.class,
            groups = "login"
    )
    @Description("Test Login Functionality: Including valid and invalid credentials")
    @Parameters({"menuName", "expectedEndPoint"})
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin(boolean successful, String userName, String password, String message) {

//        HomePage homePage = new HomePage(getPage());
//
//        final LoginModal loginModal = homePage.clickLoginMenu();
//        final Locator form = loginModal.getDialog();
//
//        Allure.step("Assertion: 'Log in' Form is visible.");
//        assertThat(form).isVisible();
//
//        loginModal
//                .inputUserName(userName)
//                .inputPassword(password)
//                .verifyAlertMessage(message)
//                .clickLoginButton()
//        ;
//
//        final Locator welcome = new HomePage(getPage()).getWelcomeMessage();
//        final Locator logOut = new HomePage(getPage()).getLogOutItem();
//        final Locator login = new HomePage(getPage()).getLoginItem();
//
//        if (successful) {
//            Allure.step("Assertion: Greeting is visible.");
//            assertThat(welcome).isVisible();
//
//            Allure.step("Assertion: 'Log out' menu is visible.");
//            assertThat(logOut).isVisible();
//
//            Allure.step("Assertion: 'Log in' menu is NOT visible.");
//            assertThat(login).not().isVisible();
//        } else {
//            Allure.step("Assertion: Greeting is NOT visible.");
//            assertThat(welcome).not().isVisible();
//
//            Allure.step("Assertion: 'Log out' menu is NOT visible.");
//            assertThat(logOut).not().isVisible();
//
//            Allure.step("Assertion: 'Log in' menu is visible.");
//            assertThat(login).isVisible();
//        }
    }
}