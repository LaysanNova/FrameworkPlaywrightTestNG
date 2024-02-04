package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.model.Footer;

public class HomePage extends Footer<HomePage> {
    private final Locator phonesCategory = link("Phones");
    private final Locator laptopsCategory = link("Laptops");
    private final Locator monitorsCategory = link("Monitors");

    public HomePage(Page page) {

        super(page);
    }

    public HomePage clickPhonesCategory() {
        phonesCategory.click();

        return this;
    }
}



// file:///C:/Users/laysa/OneDrive/Desktop/QA%20Books/Checklist_for_Testing_of_web_Application_1684852084.pdf