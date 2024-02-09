package tests.helpers;

import com.microsoft.playwright.Locator;

public class TestUtils {

    public static String[] getLocatorContext(Locator locator) {

        return locator.innerText().split("\n");

    }
}
