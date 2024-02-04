package pages;

import com.microsoft.playwright.Page;
import pages.model.FormPage;

public class SignupModal extends FormPage<SignupModal> {

    public SignupModal(Page page) {
        super(page);
    }
}
