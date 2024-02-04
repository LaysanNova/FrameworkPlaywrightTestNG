package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.FormPage;

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
    public void clickLoginButton() {

        loginButton.click();
    }
}
