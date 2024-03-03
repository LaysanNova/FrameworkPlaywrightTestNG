package tests.helpers;

import org.testng.annotations.DataProvider;
import utils.api.APIData;
import utils.runner.ProjectProperties;

public final class TestData {
    public static final String BASE_URL = ProjectProperties.BASE_URL;
    public static final String _END_POINT = "/";
    public static final String HOME_END_POINT = "/index.html";
    public static final String CART = "/cart.html";
    public static final String CONTACT = "/";
    public static final String ABOUT_US = "/";
    public static final String LOGIN = "/";
    public static final String LOG_OUT = HOME_END_POINT;
    public static final String SIGN_UP = "/";
    public static final String TITLE = "STORE";
    public static final String TITLE_CONTACT = "New message";
    public static final String TITLE_ABOUT_US = "About us";
    public static final String TITLE_LOGIN = "Log in";
    public static final String TITLE_SIGNUP = "Sign up";

    public static final String[] contactModalHeader = {"New message", "×"};
    public static final String[] contactModalBody = {"Contact Email:", "Contact Name:", "Message:"};
    public static final String[] contactModalFooter = {"Close", "Send message"};

    public static final String NAME = "Name";
    public static final String COUNTRY = "Country";
    public static final String CITY = "City";
    public static final String CREDIT_CARD = "0000000000000";
    public static final String MONTH = "Month";
    public static final String YEAR = "Year";
    public static final String successMessage = "Thank you for your purchase!";

    public static final int maxProductsOnPage = 9;

    @DataProvider
    public static Object[][] menuItemsLoggedIn() {
        return new Object[][]{
                {"Home", HOME_END_POINT},
                {"Contact", CONTACT},
                {"About us", ABOUT_US},
                {"Cart", CART},
                {"Log out", LOG_OUT}
        };
    }

    @DataProvider
    public static Object[][] menuItemsLoggedOut() {
        return new Object[][]{
                {"Home", HOME_END_POINT},
                {"Contact", HOME_END_POINT},
                {"About us", HOME_END_POINT},
                {"Cart", CART},
                {"Log in", HOME_END_POINT},
                {"Sign up", HOME_END_POINT}
        };
    }
    @DataProvider
    public static Object[][] modalTitle() {
        return new Object[][]{
                {"Contact", TITLE_CONTACT},
                {"About us", TITLE_ABOUT_US},
                {"Log in", TITLE_LOGIN},
                {"Sign up", TITLE_SIGNUP}
        };
    }

    @DataProvider
    public static Object[][] category() {
        return new Object[][]{
                {"Phones", APIData.PHONES},
                {"Monitors", APIData.MONITORS},
                {"Laptops", APIData.LAPTOPS}

        };
    }

}
