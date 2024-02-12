package pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public abstract class FormPage extends PopDialog {

    private final Locator modalTitle = locator("h5#exampleModalLabel");
    private final Locator modalHeader = locator("div.modal-header");
    private final Locator modalBody = locator("div.modal-body");
    private final Locator modalFooter = locator("div.modal-footer");
    private final Locator dialog = dialog();

    protected FormPage(Page page) {

        super(page);
    }

    public Locator getModalTitle() {

        return modalTitle;
    }

    public Locator getModalHeader() {

        return modalHeader;
    }

    public Locator getModalBody() {

        return modalBody;
    }

    public Locator getModalFooter() {

        return modalFooter;
    }

    public Locator getDialog() {

        return dialog;
    }

//    @Step("Verifying message from alert dialog.")
//    public TPage verifyAlertMessage(String message) {
//
//        getPage().onceDialog(dialog -> {
//            String actualMessage = dialog.message();
//            Assert.assertTrue(actualMessage.contains(message),
//                    "If FAIL: Alert message expected to be: " + message + " but " + actualMessage + ".");
//            dialog.dismiss();
//        });
//
//        return (TPage) this;
//    }
}
