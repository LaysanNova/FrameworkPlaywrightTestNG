package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.PreconditionPage;

import java.util.List;

public class CartTest extends BaseTest {

    @Test(testName = "TC.XXX.XX: Product Removal from Cart.")
    @Description("Objective: This test verifies that products are correctly removed" +
            " from the shopping cart when the delete action is performed.")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductRemovalFromCart() {

        new PreconditionPage(getPage()).addProductsToCart();

        CartPage cartPage = new HomePage(getPage())
                .clickCartMenu();

        final List<Locator> productList =
                cartPage
                        .getProductsList();

        int productCount = productList.size();

        List<Locator> productListAfterDeleting;

        for (Locator product : productList) {

            cartPage.clickFirstDelete();
            productCount-- ;

            productListAfterDeleting = cartPage.getProductsList();

            Allure.step("Validates that after deleting a product from the shopping cart, " +
                    "the number of products remaining in the cart is reduced by exactly one.");
            Assert.assertEquals(productListAfterDeleting.size(), productCount);
        }
    }
}
