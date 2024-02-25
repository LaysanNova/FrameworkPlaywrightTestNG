package pages.model;

import com.microsoft.playwright.Dialog;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public abstract class PopDialog<TPage> extends BaseLocator {

    PopDialog(Page page) {
        super(page);
    }

    @Step("Click 'OK' button")
    public TPage clickOk() {
        getPage().onDialog(Dialog::accept);
        getPage().waitForTimeout(2000);

        return (TPage)this;
    }
}
