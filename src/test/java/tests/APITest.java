package tests;

import com.google.gson.*;
import com.microsoft.playwright.Locator;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProdPage;
import tests.helpers.TestData;
import tests.helpers.TestUtils;
import utils.api.APIData;

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

    @Test(testName = "TC.XXX.XX: API Product View Display.")
    @Description("Objective: Verify the appearance and accuracy of the product view on the webpage and match backend.")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductViewDisplayAPI() {

        HomePage homePage = new HomePage(getPage());

        final int nth = homePage.getRandomNumber();

        ProdPage prodPage =
                homePage
                        .clickProductByNumber(nth);

        final JsonObject itemAPI = utils.api.APIUtils.getProductView(TestUtils.convertToString(nth + 1));
        final Locator imageOnPage = prodPage.getImg();
        final Double priceOnPage = TestUtils.convertToDouble(prodPage.getProductPrice());
        final String descOnPage = prodPage.getProductDesc();
        final String titleOnPage = prodPage.getProductTitle().trim();

        Allure.step("Assert that image is valid.");
        Assert.assertEquals(itemAPI.get("img").getAsString(), imageOnPage.getAttribute("src"));

        Allure.step("Assert that the product title/name is correctly displayed and matches the API.");
        Assert.assertEquals(
                itemAPI.get("title").getAsString().trim(),
                titleOnPage
        );

        Assert.assertEquals(
                TestUtils.convertToDouble(itemAPI.get("price").getAsString()),
                priceOnPage
        );

        Assert.assertEquals(
                TestUtils.makeString(itemAPI.get("desc")),
                descOnPage
        );
    }

    @Test(testName = "TC.XXX.XX: Validate API Cart Integration with UI.")
    @Description("Objective: Validating the integration between the backend API cart functionality and the user interface (UI) of the application.")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidateAPICartIntegrationWithUI() {

        JsonArray initialCartState = utils.api.APIUtils.getProductInTheCart();
        final int amountOfProduct = initialCartState.size();

        HomePage homePage = new HomePage(getPage());

        final int nth = homePage.getRandomNumber();

        ProdPage prodPage =
                homePage
                        .clickProductByNumber(nth)
                        .clickAddToCartButton()
                        .clickOk();

        JsonArray cartStateAfterAdding = utils.api.APIUtils.getProductInTheCart();

        final int amountOfProductAfterAdding = cartStateAfterAdding.size();
        final int productId = cartStateAfterAdding.get(amountOfProductAfterAdding - 1).getAsJsonObject().get("prod_id").getAsInt() - 1;

        Allure.step("Assert that size of cart increased by 1.");
        Assert.assertEquals(amountOfProduct + 1, amountOfProductAfterAdding);

        Allure.step("Assert that added product is match.");
        Assert.assertEquals(productId, nth);

        prodPage
                .clickCartMenu()
                .clickFirstDelete();

        JsonArray cartStateAfterDeleting = utils.api.APIUtils.getProductInTheCart();
        final int amountOfProductAfterDeleting = cartStateAfterDeleting.size();

        Allure.step("Assert that size of cart decreased by 1.");
        Assert.assertEquals(amountOfProductAfterDeleting, amountOfProductAfterAdding - 1);
    }

    @Test(testName = "TC.XXX.XX: Validation API cart after purchase.")
    @Description("Objective: To validate the cart gets empty after purchase process.")
    @Severity(SeverityLevel.CRITICAL)
    public void testPurchaseWithCartValidationAPI() {

        ProdPage prodPage =
                new HomePage(getPage())
                        .clickRandomCategory()
                        .clickRandomProduct()
                        .clickAddToCartButton()
                        .clickOk();


        final JsonArray cartState = utils.api.APIUtils.getProductInTheCart();

        Allure.step("Assert that cart is not empty after adding product.");
        Assert.assertFalse(cartState.isEmpty(),
                "If FAIL: The cart is EMPTY after adding product.\n");

        prodPage
                .clickCartMenu()
                .clickPlaceOrderButton()
                .enterName()
                .enterCountry()
                .enterCity()
                .enterCreditCard()
                .enterMonth()
                .enterYear()
                .clickPurchaseButton();

        final JsonArray cartStateAfterPurchase  = utils.api.APIUtils.getProductInTheCart();

        Allure.step("Assert that cart is empty after purchase process.");
        Assert.assertTrue(cartStateAfterPurchase.isEmpty(),
                "If FAIL: The cart is NOT empty after purchase process.\n");
    }
}
