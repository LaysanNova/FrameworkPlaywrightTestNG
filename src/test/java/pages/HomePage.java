package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.Footer;

import java.util.List;

public class HomePage extends Footer<HomePage> implements IRandom{

    private final  List<Locator> allCategories = locator("#itemc").all();
    private final Locator randomCategory = getRandomValue(allCategories);
    private final Locator phonesCategory = link("Phones");
    private final Locator laptopsCategory = link("Laptops");
    private final Locator monitorsCategory = link("Monitors");
    private final Locator previousButton = locator("button[id='prev2']");
    private final Locator nextButton = locator("button[id='next2']");

    public HomePage(Page page) {

        super(page);
    }

    public HomePage clickPhonesCategory() {
        phonesCategory.click();
        getPage().waitForLoadState();

        return this;
    }

    @Step("Click random Category.")
    public HomePage clickRandomCategory() {
        randomCategory.click();
        getPage().waitForLoadState();

        return this;
    }

    private List<Locator> getProductsListOfCategory() {

        return allElements("#tbodyid > div");
    }

    public Locator getRandomProduct() {
        List<Locator> allProducts = getProductsListOfCategory();

        return getRandomValue(allProducts);
    }

    @Step("Click random product.")
    public ProdPage clickRandomProduct() {

        Locator randomProduct = getRandomProduct();
        randomProduct.locator("h4.card-title > a").click();

        return new ProdPage(getPage());
    }
}


// file:///C:/Users/laysa/OneDrive/Desktop/QA%20Books/Checklist_for_Testing_of_web_Application_1684852084.pdf