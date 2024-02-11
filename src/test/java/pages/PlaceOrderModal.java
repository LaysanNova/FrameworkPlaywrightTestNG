package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.FormPage;
import tests.helpers.TestData;

public class PlaceOrderModal extends FormPage<PlaceOrderModal> {

    private final Locator total = locator("form label[id='totalm']");
    private final Locator nameInput = locator("input[id='name']");
    private final Locator countryInput = locator("input[id='country']");
    private final Locator cityInput = locator("input[id='city']");
    private final Locator creditCardInput = locator("input[id='card']");
    private final Locator monthInput = locator("input[id='month']");
    private final Locator yearInput = locator("input[id='year']");
    private final Locator purchaseButton = button("Purchase");

    protected PlaceOrderModal(Page page) {

        super(page);
    }

    public String getTotal() {
        return total.innerText().split(" ")[1];
    }

    @Step("Enter name.")
    public PlaceOrderModal enterName() {
        nameInput.fill(TestData.NAME);

        return this;
    }

    @Step("Enter country.")
    public PlaceOrderModal enterCountry() {
        countryInput.fill(TestData.COUNTRY);

        return this;
    }

    @Step("Enter city.")
    public PlaceOrderModal enterCity() {
        cityInput.fill(TestData.CITY);

        return this;
    }

    @Step("Enter credit card.")
    public PlaceOrderModal enterCreditCard() {
        creditCardInput.fill(TestData.CREDIT_CARD);

        return this;
    }

    @Step("Enter month.")
    public PlaceOrderModal enterMonth() {
        monthInput.fill(TestData.MONTH);

        return this;
    }

    @Step("Enter year.")
    public PlaceOrderModal enterYear() {
        yearInput.fill(TestData.YEAR);

        return this;
    }

    @Step("Click 'Purchase' button.")
    public ConfirmationModal clickPurchaseButton() {
        purchaseButton.click();

        return new ConfirmationModal(getPage());
    }
}
