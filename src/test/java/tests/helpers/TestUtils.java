package tests.helpers;

import com.microsoft.playwright.Locator;

public class TestUtils {

    public static String[] getLocatorContext(Locator locator) {

        return locator.innerText().split("\n");

    }

    public static String extractPrice(Locator locator) {

        return locator.innerText().split(" ")[0].replace("$", "");
    }
}
