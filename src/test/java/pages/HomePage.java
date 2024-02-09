package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;
import pages.model.Footer;

import java.util.List;

public class HomePage extends Footer<HomePage> implements IRandom{

    private final  List<Locator> allCategories = locator("#itemc").all();
    private final Locator randomCategory = getRandomValue(allCategories);
    private final Locator phonesCategory = link("Phones");
    private final Locator laptopsCategory = link("Laptops");
    private final Locator monitorsCategory = link("Monitors");
    private Locator product;

    private final List<Locator> allProductsCars = allProducts("#tbodyid > div");
    private final Locator randomProduct = getRandomValue(allProductsCars);

    public HomePage(Page page) {

        super(page);
    }

    public HomePage clickPhonesCategory() {
        phonesCategory.click();

        return this;
    }

    @Step("Click random ProductTitle.")
    public ProdPage clickRandomProductTitle() {
        getTitle().click();

        return new ProdPage(getPage());
    }

    @Step("Click random Category.")
    public HomePage clickRandomCategory() {
        randomCategory.click();

        return this;
    }

    public Locator getTitle() {

        return randomProduct.locator("h4.card-title > a");
    }


}


// file:///C:/Users/laysa/OneDrive/Desktop/QA%20Books/Checklist_for_Testing_of_web_Application_1684852084.pdf