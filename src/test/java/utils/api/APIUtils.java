package utils.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import utils.reports.LoggerUtils;

public class APIUtils {

    static void checkStatus(APIResponse apiResponse, String methodName) {
        if (apiResponse.status() < 200 || apiResponse.status() >= 300) {
            LoggerUtils.logException("EXCEPTION: API request FAILED. \n" +
                    "response status: " + apiResponse.status()
                    + "\n" + methodName);
            Assert.fail();
        } else {
            LoggerUtils.logInfo("API: '" + methodName + "' status " + apiResponse.status());
        }
    }

    static JsonObject initJsonObject(String apiResponseBody) {
        JsonObject object = new JsonObject();
        try {
            return new Gson().fromJson(apiResponseBody, JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    private static void checkLoginResponse(JsonObject loginResponse) {

        try {
            JsonObject item = loginResponse.getAsJsonObject("Item");
            String token = item.get("token").getAsString();
            String expiration = item.get("expiration").getAsString();
            String username = item.get("username").getAsString();

            if (token.isEmpty() || expiration.isEmpty() || username.isEmpty()) {
                LoggerUtils.logException("API: ERROR: LOGIN"+
                        "\ntoken is empty = " + token.isEmpty() +
                        "\nexpiration is empty = " + expiration.isEmpty() +
                        "\nusername is empty = " + username.isEmpty()
                );
                Assert.fail();
            }

        } catch (Exception e) {
            LoggerUtils.logException("API: EXCEPTION: FAILED to extract token, expiration, username");
            Assert.fail();
        }
    }

    public static void check(String authToken) {

        final APIResponse apiResponse = APIServices.check(authToken);

        checkStatus(apiResponse, "Check");

        final JsonObject loginResponse = initJsonObject(apiResponse.text());

        checkLoginResponse(loginResponse);
    }

    public static void login() {

        final APIResponse loginResponse = APIServices.login();

        checkStatus(loginResponse, "Log in");

        try {
            String authToken = loginResponse.text().split(": ")[1].substring(0,  loginResponse.text().split(": ")[1].length()-2);
            check(authToken);
        }
        catch (Exception e) {
            LoggerUtils.logException("API: EXCEPTION: FAILED to extract Auth_token");
            Assert.fail();
        }
    }
}
