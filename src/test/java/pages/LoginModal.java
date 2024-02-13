package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.model.FormPage;
import tests.helpers.TestData;

public class LoginModal extends FormPage<LoginModal> {

    private final Locator inputUserName = locator("input[id='loginusername']");
    private final Locator inputPassword = locator("input[id='loginpassword']");
    private final Locator loginButton = button("Log in");

    public LoginModal(Page page) {
        super(page);
    }

    @Step("Enter user name.")
    public LoginModal inputUserName(String userName) {
        inputUserName.fill(userName);

        return this;
    }

    @Step("Enter password name.")
    public LoginModal inputPassword(String password) {
        inputPassword.fill(password);

        return this;
    }

    @Step("Click 'Log in' button.")
    public HomePage clickLoginButton() {

        loginButton.click();
        return new HomePage(getPage());
    }

    @Step("Verifying message from alert dialog.")
    public LoginModal verifyAlertMessage(String message) {

        getPage().onceDialog(dialog -> {
            String actualMessage = dialog.message();
            Assert.assertTrue(actualMessage.contains(message),
                    "If FAIL: Alert message expected to be: " + message + " but " + actualMessage + ".");
            dialog.dismiss();
        });

        return this;
    }
}
