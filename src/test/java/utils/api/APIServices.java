package utils.api;


import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import utils.runner.ProjectProperties;

import java.util.HashMap;
import java.util.Map;

public class APIServices {
    static Playwright playwright = Playwright.create();
    public static APIResponse check(String token) {

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);

        return playwright.request().newContext()
                .post(
                        ProjectProperties.API_BASE_URL + APIData.CHECK,
                        RequestOptions.create()
                                .setData(data)
                );
    }

    public static APIResponse login() {

        Map<String, Object> data = new HashMap<>();
        data.put("username", ProjectProperties.USERNAME);
        data.put("password", ProjectProperties.E_PASSWORD);

        return playwright.request().newContext()
                .post(
                        ProjectProperties.API_BASE_URL + APIData.LOGIN,
                        RequestOptions.create()
                                .setData(data)
                );
    }
}
