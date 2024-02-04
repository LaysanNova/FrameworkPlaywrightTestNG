package pages;

import com.microsoft.playwright.Page;
import pages.model.FormPage;

public class ContactModal extends FormPage<ContactModal> {

    public ContactModal(Page page) {
        super(page);
    }
}
