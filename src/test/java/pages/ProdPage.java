package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.HeadMenu;
import tests.helpers.TestUtils;

public class ProdPage extends HeadMenu<ProdPage> {

    final Locator addToCartButton =  link("Add to cart");
    final Locator _$price =  locator("h3.price-container");
    final Locator img =  locator("div.carousel-inner img");
    final Locator productBody =  locator("#tbodyid");
    final Locator productTitle =  productBody.locator("h2");
    final Locator productPrice =  productBody.locator("h3");
    final Locator productDesc =  productBody.locator("p");

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

    public Locator getImg() {
        return img;
    }

    public Locator getProductBody() {

        return productBody;
    }

    public String getProductTitle() {

        return productTitle.innerText().trim();
    }

    public String getProductPrice() {

        return TestUtils.extractPrice(productPrice);
    }
    public String getProductDesc() {

        return productDesc.innerText().trim();
    }
}
