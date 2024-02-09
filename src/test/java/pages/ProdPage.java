package pages;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.model.Footer;
import tests.BaseTest;

public class ProdPage extends Footer<ProdPage> {

    Locator addToCartButton =  link("Add to cart");

    protected ProdPage(Page page) {
        super(page);
    }

    @Step("Click 'Add to cart' button")
    public ProdPage clickAddToCartButton() {
        addToCartButton.click();

        return this;
    }

    @Step("Click 'Yes' Product added")
    public ProdPage clickOk() {
        getPage().onDialog(Dialog::accept);

        return this;
    }
}
