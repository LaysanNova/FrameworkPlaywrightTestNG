package tests.helpers;

import org.testng.annotations.DataProvider;
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
    public static Object[][] menuItems() {
        return new Object[][]{
                {"Home", HOME_END_POINT},
                {"Contact", CONTACT},
                {"About us", ABOUT_US},
                {"Cart", CART},
                {"Log in", LOGIN},
                {"Sign up", SIGN_UP}
        };
    }
}
