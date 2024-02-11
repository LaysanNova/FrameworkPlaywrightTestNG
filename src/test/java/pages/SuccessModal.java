package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.FormPage;
import tests.helpers.TestData;

public class SuccessModal extends FormPage<SuccessModal> {

    private final Locator okButton = button("OK");
    private final Locator successMessage = heading(TestData.successMessage);
//    private final Locator purchaseData = locator("p");
    private final Locator successSymbol = locator(".sa-success");

    public SuccessModal(Page page) {
        super(page);
    }

    @Step("Click 'OK' button.")
    public HomePage clickOkButton() {
        okButton.click();

        return new HomePage(getPage());
    }

    @Step("Get success message of purchase.")
    public Locator getSuccessMessage() {
        return successMessage;
    }

//    public String getPurchaseData() {
//        return purchaseData.innerText();
//    }

    public Locator getSuccessSymbol() {
        return successSymbol;
    }
}
