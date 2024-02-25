package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.HeadMenu;

import java.util.List;

public class CartPage extends HeadMenu<CartPage> {

    private final List<Locator> products = allElements("#tbodyid > tr");
    private final Locator placeOrderButton = button("Place Order");
    private final Locator total = locator("h3[id='totalp']");
    private final Locator delete = link("Delete");

    public CartPage(Page page) {
        super(page);
    }

    public List<Locator> getProducts() {

        return products;
    }

    @Step("Get 'Total'.")
    public String getTotal() {

        return total.innerText();
    }

    @Step("Click 'Place Order' button.")
    public PlaceOrderModal clickPlaceOrderButton() {
        placeOrderButton.click();

        return new PlaceOrderModal(getPage());
    }

    @Step("Click 'Delete'.")
    public CartPage clickFirstDelete() {
        delete.nth(0).click();
        getPage().waitForTimeout(3000);

        return this;
    }
}
