package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.model.FormPage;

public class ContactModal extends FormPage<ContactModal> {

    Locator contactEmailInput = label("Contact Email:");
    Locator contactNameInput = label("Contact Name:");
    Locator contactMessageInput = label("Message:");

    public ContactModal(Page page) {

        super(page);
    }

    public Locator getContactEmailInput() {

        return contactEmailInput;
    }

    public Locator getContactNameInput() {

        return contactNameInput;
    }

    public Locator getContactMessageInput() {

        return contactMessageInput;
    }
}




//            page.getByText("Contact Email:").click();
//            page.locator("#recipient-email").click();
//            page.getByText("Contact Name:").click();
//            page.getByLabel("Contact Email:").click();
//            page.getByText("Message:").click();
//            page.getByLabel("Message:").click();
//            page.getByLabel("New message").getByText("Close").click();
//            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact")).click();
//            page.onceDialog(dialog -> {
//                System.out.println(String.format("Dialog message: %s", dialog.message()));
//                dialog.dismiss();
//            });
//            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send message")).click();
//        }
//    }
//}