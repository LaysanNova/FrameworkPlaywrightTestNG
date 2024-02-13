package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.Footer;
import pages.model.HeadMenu;

import java.util.List;

public class HomePage extends HeadMenu<HomePage> implements IRandom{

    private final  List<Locator> allCategories = locator("#itemc").all();
    private final Locator randomCategory = getRandomValue(allCategories);
    private final Locator phonesCategory = link("Phones");
    private final Locator laptopsCategory = link("Laptops");
    private final Locator monitorsCategory = link("Monitors");
    private final Locator previousButton = locator("button[id='prev2']");
    private final Locator nextButton = locator("button[id='next2']");
    private final Footer footer = new Footer();

    public HomePage(Page page) {

        super(page);
    }

    @Step("Click 'Phones' Category.")
    public HomePage clickPhonesCategory() {
        phonesCategory.click();
        getPage().waitForLoadState();

        return this;
    }

    @Step("Click 'Laptops' Category.")
    public HomePage clickLaptopsCategory() {
        laptopsCategory.click();

        return this;
    }

    @Step("Click 'Monitors' Category.")
    public HomePage clickMonitorsCategory() {
        monitorsCategory.click();

        return this;
    }

    public HomePage clickCategory(String category) {

        switch (category) {
            case "Phones" -> clickPhonesCategory();
            case "Laptops" -> clickLaptopsCategory();
            case "Monitors" -> clickMonitorsCategory();
        }

        return this;
    }

    @Step("Click random Category.")
    public HomePage clickRandomCategory() {
        randomCategory.click();

        return this;
    }

    @Step("Get Products List displayed on the page.")
    public List<Locator> getProductsListOfCategory() {

        getPage().waitForTimeout(2000);

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
