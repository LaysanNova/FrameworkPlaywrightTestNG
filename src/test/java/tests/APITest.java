package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProdPage;
import tests.helpers.TestData;
import tests.helpers.TestUtils;
import utils.api.APIData;

import javax.swing.text.Utilities;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class APITest extends BaseTest {

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

    @Test(testName = "TC.XXX.XX: Product View Display.")
    @Description("Objective: Verify the appearance and accuracy of the product view on the webpage and match backend.")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductViewDisplayAPI() {

//        HomePage homePage = new HomePage(getPage());
//
//        final int nth = homePage.getRandomNumber();
//        final Locator product = homePage.getProductByNumber(nth);
//        final Map<String, String> productData = TestUtils.getData(product);
//
//        final JsonObject item = utils.api.APIUtils.getProductView(TestUtils.convertToString(nth));
//
////        Allure.step("Assert that image is value.");
////        Assert.assertEquals(item.get("img"), productData.get("image"));
//
//        Allure.step("that the product title/name is correctly displayed and matches the intended product.");
//        Assert.assertEquals(item.get("title"), productData.get("title"));
//        Assert.assertEquals(item.get("price"), productData.get("price"));
//        Assert.assertEquals(item.get("desc"), productData.get("desc"));

    }
}
