package pages;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.model.BaseMenu;

import tests.BaseTest;

public class ProdPage extends BaseMenu {

    final Locator addToCartButton =  link("Add to cart");
    final Locator _$price =  locator("h3.price-container");

    protected ProdPage(Page page) {
        super(page);
    }

    @Step("Click 'Add to cart' button")
    public ProdPage clickAddToCartButton() {
        addToCartButton.click();

        return this;
    }

    public Locator get_$price() {
        return _$price;
    }
}
