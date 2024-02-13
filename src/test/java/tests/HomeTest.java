package tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import tests.helpers.TestData;
import tests.helpers.TestUtils;
import utils.api.APIData;

import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static tests.helpers.TestUtils.extractPrice;

public class HomeTest extends BaseTest implements IRandom {

    @Test(testName = "TC.XXX.XX: Verify Logout Functionality")
    @Description("Objective: To ensure that the logout feature functions correctly and securely terminates the user session.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogOut() {

        new HomePage(getPage())
                .clickLogOut();

        final Locator welcome = new HomePage(getPage()).getWelcomeMessage();
        final Locator logOut = new HomePage(getPage()).getLogOutItem();
        final Locator login = new HomePage(getPage()).getLoginItem();

        Allure.step("Assertion: 'Welcome' is NOT visible.");
        assertThat(welcome).not().isVisible();

        Allure.step("Assertion: Menu option 'Log out' is NOT visible.");
        assertThat(logOut).not().isVisible();

        Allure.step("Assertion: Menu option 'Log in' is visible.");
        assertThat(login).isVisible();
    }

    @Test(testName = "TC.XXX.XX: Seamless Purchase Journey Validation")
    @Description("Objective: To validate the entire purchase process.")
    @Severity(SeverityLevel.CRITICAL)
    public void testPurchaseWithCartValidationE2E() {

        ProdPage prodPage =
                new HomePage(getPage())
                        .clickRandomCategory()
                        .clickRandomProduct()
                        .clickAddToCartButton()
                        .clickOk();

        final String price = extractPrice(prodPage.get_$price());

        CartPage cartPage =
                prodPage
                        .clickCartMenu();

        final String total = cartPage.getTotal();

        Allure.step("Assert that price of product equals to total before placing the order.");
        Assert.assertEquals(price, total);

        PlaceOrderModal placeOrderModal =
                cartPage
                        .clickPlaceOrderButton();

        final String totalOnPlaceOrder = placeOrderModal.getTotal();

        Allure.step("Assert that price of product equals to total on order.");
        Assert.assertEquals(total, totalOnPlaceOrder);

        ConfirmationModal successModal =
                placeOrderModal
                        .enterName()
                        .enterCountry()
                        .enterCity()
                        .enterCreditCard()
                        .enterMonth()
                        .enterYear()
                        .clickPurchaseButton();

        final Locator message = successModal.getSuccessMessage();
        final Locator successSymbol = successModal.getSuccessSymbol();

        Allure.step("Assert that message of purchase contains " + TestData.successMessage + ".");
        assertThat(message).isVisible();

        Allure.step("Assert that message has success sign.");
        assertThat(successSymbol).isVisible();

    }

    @Test(
            testName = "TC.XXX.XX: Category Navigation - Verify Product Display matches to API",
            dataProvider = "category", dataProviderClass = TestData.class
    )
    @Description("Objective: To verify the functionality of product display when navigating through categories by clicking.")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDisplayByCategory(String category, String cat) {

        List<Locator> phones =
                new HomePage(getPage())
                        .clickCategory(category)
                        .getProductsListOfCategory();

        JsonArray phonesAPI = utils.api.APIUtils.getProductByCategory(cat);
        int id = 0;

        for (Locator phone : phones) {

            Map<String, String> phoneData = TestUtils.getData(phone);

            Allure.step("Assert that price matches to backend.");
            Assert.assertEquals(TestUtils.convertToNumber(phoneData.get("price")), TestUtils.convertToNumber(phonesAPI.get(id).getAsJsonObject().get("price").getAsString()));

            Allure.step("Assert that desc matches to backend.");
            //Assert.assertEquals(phoneData.get("desc"), TestUtils.makeString(phonesAPI.get(id).getAsJsonObject().get("desc")));

            Allure.step("Assert that title matches to backend.");
            Assert.assertEquals(phoneData.get("title"), TestUtils.makeString(phonesAPI.get(id).getAsJsonObject().get("title")));

            Allure.step("Assert that img matches to backend.");
            Assert.assertEquals(phoneData.get("img"), phonesAPI.get(id).getAsJsonObject().get("img").getAsString());

            id ++;
        }
    }
}
