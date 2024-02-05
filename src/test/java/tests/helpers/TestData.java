package tests.helpers;

import org.testng.annotations.DataProvider;
import pages.HomePage;
import utils.runner.ProjectProperties;

public final class TestData {
    public static final String BASE_URL = "https://www.demoblaze.com";
    public static final String _END_POINT = "/";
    public static final String HOME_END_POINT = "/index.html";
    public static final String CART = "/cart.html";
    public static final String CONTACT = "/";
    public static final String ABOUT_US = "/";
    public static final String LOGIN = "/";
    public static final String LOG_OUT = HOME_END_POINT;
    public static final String SIGN_UP = "/";
    public static final String TITLE = "STORE";

    @DataProvider
    public static Object[][] menuItemsLoggedIn() {
        return new Object[][]{
                {"Home", HOME_END_POINT},
                {"Contact", CONTACT},
                {"About us", ABOUT_US},
                {"Cart", CART},
                {"Log out", LOG_OUT},
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
}
