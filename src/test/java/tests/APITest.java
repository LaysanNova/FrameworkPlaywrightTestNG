package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.helpers.TestData;
import tests.helpers.TestUtils;
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

    @Test(testName = "TC.XXX.XX: Product Quantity Verification")
    @Description("Objective: Verify that the correct quantity of products is displayed on the webpage.")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDisplayOnThePageAPI() {

        final JsonObject products = utils.api.APIUtils.getProducts();
        final JsonArray items =  products.getAsJsonArray("Items");
        final String lastKey = products.getAsJsonObject("LastEvaluatedKey").get("id").getAsString();

        Allure.step("Assert that quantity of products is according data.");
        Assert.assertEquals(items.size(), TestData.maxProductsOnPage);
        Assert.assertEquals(TestUtils.convertToInt(String.valueOf(lastKey)), TestData.maxProductsOnPage);

        final JsonObject nextProducts = utils.api.APIUtils.getNextProducts(TestUtils.convertToString(TestUtils.convertToInt(lastKey)));
        final JsonArray nextItems = nextProducts.getAsJsonArray("Items");
        final String lastEvaluatedKey = nextProducts.getAsJsonObject("LastEvaluatedKey").get("id").getAsString();
        final String scannedCount = nextProducts.get("ScannedCount").getAsString();

        Allure.step("Assert that quantity of next products is according data.");
        Assert.assertTrue(
                TestUtils.convertToInt(scannedCount) == TestData.maxProductsOnPage
                        || TestUtils.convertToInt(scannedCount) < TestData.maxProductsOnPage);

        Allure.step("Assert that 'lastEvaluatedKey' of next products is equal LastEvaluatedKey before + quantity of products.");
        Assert.assertEquals(TestData.maxProductsOnPage + nextItems.size(), TestUtils.convertToInt(lastEvaluatedKey));
    }
}
