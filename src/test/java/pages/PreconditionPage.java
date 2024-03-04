package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import pages.model.HeadMenu;
import tests.helpers.TestData;
import utils.reports.LoggerUtils;

import java.util.List;

public class PreconditionPage extends HeadMenu<PreconditionPage>  {

    public PreconditionPage(Page page) {
        super(page);
    }

    public void cleanCart() {

        final CartPage cartPage =
                new HomePage(getPage())
                        .clickCartMenu();

        List<Locator> delete = cartPage.getDeleteButtons();
        for (Locator d : delete) {
            cartPage.clickFirstDelete();
        }

        cartPage
                .clickHomeMenu();
    }

    public void addProductsToCart() {

        HomePage homePage = new HomePage(getPage());

        homePage
                .clickRandomProduct()
                .clickAddToCartButton()
                .clickOk()
                .clickHomeMenu();

        List< Locator> cartProducts = new HomePage(getPage())
                .clickCartMenu()
                .getProductsList();

        if (cartProducts.isEmpty()) {
            LoggerUtils.logError("ERROR: Precondition is failed.");
            Assert.fail();
        }
    }
}
