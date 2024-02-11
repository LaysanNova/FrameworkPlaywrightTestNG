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
}    
