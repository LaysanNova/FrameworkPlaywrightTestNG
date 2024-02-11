package utils.api;

import org.testng.annotations.DataProvider;

import java.util.List;

public final class APIData {
    public static final String CHECK = "/check";
    public static final String LOGIN = "/login";
    public static final String CATEGORY = "/bycat";

    public static final String PHONES = "phone";
    public static final String LAPTOPS = "notebook";
    public static final String MONITORS = "monitor";

    public static final String[] CATEGORIES = {PHONES, LAPTOPS, MONITORS};

}
