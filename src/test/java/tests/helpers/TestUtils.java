package tests.helpers;

import com.google.gson.JsonElement;
import com.microsoft.playwright.Locator;

import java.util.HashMap;
import java.util.Map;

public class TestUtils {

    public static String[] getLocatorContext(Locator locator) {

        return locator.innerText().split("\n");
    }

    public static String extractPrice(Locator locator) {

        return locator.innerText().split(" ")[0].replace("$", "");
    }

    public static Map<String, String> getData(Locator locator) {

        Map<String, String> data = new HashMap<>();
        data.put("price", locator.locator("h5").innerText().replace("$", ""));
        data.put("title", locator.locator("h4").innerText());
        data.put("desc", locator.locator("p").innerText().trim());
        data.put("img", locator.locator("img").getAttribute("src"));

        return data;
    }

    public static Double convertToDouble(String n) {

        return Double.parseDouble(n);
    }

    public static int convertToInt(String n) {

        return Integer.parseInt(n);
    }

    public static String convertToString(int number) {

        return String.valueOf(number);
    }

    public static String makeString(JsonElement str) {

        return str.getAsString().replace("\n", "").trim();
    }
}
