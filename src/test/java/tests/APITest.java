package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.api.APIData;

public class APITest {

    @Test(
            testName = "TC.XXX.XX: Verify Login Functionality with API",
            groups = "login")
    @Description("Test Login Functionality: To ensure that the login functionality of the " +
            "application's API works correctly,allowing users to authenticate successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginAPI() {

        utils.api.APIUtils.login();

    }

    @Test(testName = "TC.XXX.XX: Category Navigation - Verify Product Response")
    @Description("Objective: To verify the functionality of product display when navigating through categories by clicking.")
    @Parameters({"cat"})
    @Severity(SeverityLevel.NORMAL)
    public void testProductDisplayByCategoryAPI() {

        for (String cat : APIData.CATEGORIES) {

            JsonArray products = utils.api.APIUtils.getProductByCategory(cat);

            for (JsonElement product : products) {
                String category = product.getAsJsonObject().get("cat").getAsString();

                Allure.step("Assert that the product category is " + cat);
                Assert.assertEquals(category, cat);
            }
        }
    }
}
