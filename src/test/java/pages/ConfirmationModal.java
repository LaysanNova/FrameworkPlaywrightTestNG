package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.FormPage;
import tests.helpers.TestData;

public class ConfirmationModal extends FormPage<ConfirmationModal> {

    private final Locator okButton = button("OK");
    private final Locator successMessage = heading(TestData.successMessage);
    private final Locator successSymbol = locator(".sa-success");

    public ConfirmationModal(Page page) {
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

    public Locator getSuccessSymbol() {

        return successSymbol;
    }
}
