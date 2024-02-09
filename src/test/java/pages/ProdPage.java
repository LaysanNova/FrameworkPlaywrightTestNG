package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.model.Footer;
import tests.BaseTest;

public class ProdPage extends Footer<ProdPage> {

    Locator addToCartButton =  link("Add to cart");


    protected ProdPage(Page page) {
        super(page);
    }

    public ProdPage clickAddToCartButton() {
        addToCartButton.click();

        return this;
    }
}
