package pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.*;

public abstract class HeadMenu<TPage> extends PopDialog<TPage> {

    private final Locator logo = image("PRODUCT STORE");
    private final Locator homeItem = link("Home");
    private final Locator contactItem = link("Contact");
    private final Locator aboutUsItem = link("About us");
    private final Locator cartItem = exactLink("Cart");
    private final Locator loginItem = link("Log in");
    private final Locator signUpItem = link("Sign up");
    private final Locator logOutItem = link("Log out");
    private final Locator welcomeMessage = link("Welcome");

    protected HeadMenu(Page page) {

        super(page);
    }

    public Locator getLogo() {
        return logo;
    }

    public Locator getHomeItem() {
        return homeItem;
    }

    public Locator getContactItem() {
        return contactItem;
    }

    public Locator getAboutUsItem() {
        return aboutUsItem;
    }

    public Locator getCartItem() {
        return cartItem;
    }

    public Locator getLoginItem() {
        return loginItem;
    }

    public Locator getSignUpItem() {
        return signUpItem;
    }

    public Locator getLogOutItem() {
        return logOutItem;
    }

    public Locator getWelcomeMessage() {
        return welcomeMessage;
    }

    @Step("Click menu 'Logo'.")
    public HomePage clickLogo() {
        logo.click();

        return new HomePage(getPage());
    }

    @Step("Click menu 'Home'.")
    public HomePage clickHomeMenu() {
        homeItem.click();

        return new HomePage(getPage());
    }

    @Step("Click menu 'Contact'.")
    public ContactModal clickContactMenu() {
        contactItem.click();
        getPage().waitForLoadState();

        return new ContactModal(getPage());
    }

    @Step("Click menu 'About us'.")
    public AboutUsModal clickAboutUsMenu() {
        aboutUsItem.click();

        return new AboutUsModal(getPage());
    }

    @Step("Click menu 'Cart'.")
    public CartPage clickCartMenu() {
        cartItem.click();

        return new CartPage(getPage());
    }

    @Step("Click menu 'Log in'.")
    public LoginModal clickLoginMenu() {
        loginItem.click();

        return new LoginModal(getPage());
    }

    @Step("Click menu 'Sign up'.")
    public SignupModal clickSignUpMenu() {
        signUpItem.click();

        return new SignupModal(getPage());
    }

    @Step("click Log Out")
    public HomePage clickLogOut() {
        logOutItem.click();

        return new HomePage(getPage());
    }
}
