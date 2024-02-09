package tests;

import org.testng.annotations.Test;
import pages.HomePage;

public class CartTest extends BaseTest {

    @Test
    public void testPurchaseWithCartValidationE2E() {

        HomePage homePage = new HomePage(getPage());

        homePage
                .clickRandomCategory()
                .clickRandomProductTitle()
                .clickAddToCartButton()
                .clickOk();


//        getPage().waitForTimeout(10000);


    }

}
