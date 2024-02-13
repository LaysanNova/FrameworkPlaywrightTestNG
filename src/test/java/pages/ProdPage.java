package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.HeadMenu;

public class ProdPage extends HeadMenu<ProdPage> {

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
