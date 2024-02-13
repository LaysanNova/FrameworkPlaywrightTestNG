package pages.model;

import com.microsoft.playwright.Dialog;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public abstract class PopDialog<Self> extends BaseLocator {

    PopDialog(Page page) {
        super(page);
    }

    @Step("Click 'OK' button")
    public Self clickOk() {
        getPage().onDialog(Dialog::accept);

        return (Self)this;
    }
}
