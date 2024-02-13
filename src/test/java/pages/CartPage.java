package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.BaseMenu;

import java.util.List;

public class CartPage extends BaseMenu<CartPage> {

    private final List<Locator> products = allElements("#tbodyid > tr");
    private final Locator placeOrderButton = button("Place Order");
    private final Locator total = locator("h3[id='totalp']");

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
}
