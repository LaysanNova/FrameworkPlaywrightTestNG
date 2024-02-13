package pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class FormPage<TPage> extends PopDialog<TPage> {

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

}
