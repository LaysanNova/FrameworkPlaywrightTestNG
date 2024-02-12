package pages.model;

import com.microsoft.playwright.Dialog;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public abstract class PopDialog extends BaseLocator {

    PopDialog(Page page) {
        super(page);
    }

//    @Step("Click 'OK' button")
//    public TPage clickOk() {
//        getPage().onDialog(Dialog::accept);
//
//        return (TPage) this;
//    }
}
