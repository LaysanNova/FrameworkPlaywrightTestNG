package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import tests.helpers.TestData;
import utils.api.APIUtils;

import java.util.List;

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


//        Preconditions:
//        User must have a registered account on the e-commerce platform.
//                User must be logged in to the account.
//                The desired product(s) must be available for purchase and added to the shopping cart.
//                Payment methods must be set up and valid.
//                Test Steps:
//
//        Navigate to the e-commerce application's homepage.
//        Log in to the user account with valid credentials.
//                Search for the desired product using the search functionality.
//                Select the desired product from the search results.
//                Verify that the product details (name, price, quantity, etc.) are displayed correctly.
//                Click on the "Add to Cart" button.
//                Navigate to the shopping cart page and verify that the added product is displayed in the cart.
//        Proceed to checkout.
//                Verify that the correct shipping address is pre-filled (if applicable) or enter a new shipping address.
//                Select the desired shipping method.
//        Verify that the shipping cost is calculated correctly.
//        Proceed to the payment section.
//                Select a valid payment method (e.g., credit card, PayPal).
//        Enter valid payment details (e.g., credit card information).
//        Verify that the total order amount is calculated correctly including taxes and shipping costs.
//        Place the order by clicking on the "Place Order" button.
//                Verify that the order confirmation page is displayed.
//                Verify that the order summary (product details, shipping address, payment method) is correct.
//        Verify that an order confirmation email is sent to the user's registered email address.
//        Verify that the order status is updated to "Processing" or "Confirmed" in the user's account dashboard.
//        Optionally, verify that the inventory level of the purchased product is updated correctly.
//                Optionally, verify that the payment transaction is processed successfully.


//        getPage().waitForTimeout(10000);
    }

    @Test(testName = "TC.XXX.XX: Category Navigation - Verify Product Display")
    @Description("Objective: To verify the functionality of product display when navigating through categories by clicking.")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDisplayByCategory() {

        List<Locator> phones =
                new HomePage(getPage())
                        .clickPhonesCategory()
                        .getProductsListOfCategory();

        APIUtils.getProductByCategory("phone");

        System.out.println(phones);
        System.out.println(phones.size());

        List<Locator> laptops =
                new HomePage(getPage())
                        .clickLaptopsCategory()
                        .getProductsListOfCategory();

        System.out.println(laptops);
        System.out.println(laptops.size());

        List<Locator> monitors =
                new HomePage(getPage())
                        .clickMonitorsCategory()
                        .getProductsListOfCategory();

        System.out.println(monitors);
        System.out.println(monitors.size());

    }





//        Navigate to the homepage of the application.
//                Identify and click on a specific category from the category menu.
//        Ensure that the category page loads correctly.
//                Verify that products relevant to the selected category are displayed.
//                Click on multiple categories to test navigation across different categories.
//                Confirm that products change dynamically based on the selected category.
//                Repeat steps 2-6 for each category available in the application.

}
