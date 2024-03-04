package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.HeadMenu;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends HeadMenu<CartPage> {

    private final Locator productsTable = locator("#tbodyid");
    private final Locator placeOrderButton = button("Place Order");
    private final Locator total = locator("h3[id='totalp']");
    private final Locator delete = link("Delete");

    public CartPage(Page page) {
        super(page);
    }

    @Step("Collecting products on page 'Cart'.")
    public List<Locator> getProductsList() {

        List<Locator> productList = new ArrayList<>();

        getPage().waitForLoadState();

        if (!productsTable.locator("tr").all().isEmpty()) {
            productList =  allElements(".success");
            getPage().waitForTimeout(1000);
        }

        return productList;
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
        getPage().waitForTimeout(2000);

        return this;
    }

    @Step("Collecting buttons 'Delete' of products on page 'Cart'.")
    public List<Locator> getDeleteButtons() {

        List<Locator> productsList = allElements(".success");
        List<Locator> deleteButtonsList = new ArrayList<>();

        if (!productsList.isEmpty()) {
            for (Locator product : productsList) {
                deleteButtonsList.add(product.locator("a"));
            }
        }

        return deleteButtonsList;
    }
}
