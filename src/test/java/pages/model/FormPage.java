package pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class FormPage<TPage> extends BaseLocator<TPage> {

    private final Locator closeButton = image("PRODUCT STORE");
    private final Locator dialog = dialog();

    protected FormPage(Page page) {
        super(page);
    }

    public Locator getCloseButton() {

        Allure.step("Assertion: 'Close BTN on Form' is visible.");
        assertThat(closeButton).isVisible();

        return closeButton;
    }

    public Locator getDialog() {

        return dialog;
    }

    @Step("Verifying message from alert dialog.")
    public TPage verifyAlertMessage(String message) {

        getPage().onceDialog(dialog -> {
            String actualMessage = dialog.message();
            Assert.assertTrue(actualMessage.contains(message),
                    "If FAIL: Alert message expected to be: " + message + " but " + actualMessage + ".");
            dialog.dismiss();
        });

        return (TPage) this;
    }
}
