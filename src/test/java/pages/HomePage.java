package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.Footer;

import java.util.List;

public class HomePage extends Footer<HomePage> implements IRandom{
    private final Locator phonesCategory = link("Phones");
    private final Locator laptopsCategory = link("Laptops");
    private final Locator monitorsCategory = link("Monitors");
    private Locator product;
    private final List<Locator> allProductsTitles = locator("#tbodyid div").all();
    private final List<Locator> cardTitle = locator("h4.card-title").all();

    private final int randomIndex = getRandomInt(0, allProductsTitles.size());

    public HomePage(Page page) {

        super(page);
    }

    private List<Locator> getProducts() {
        return allProductsTitles;
    }

    public HomePage clickPhonesCategory() {
        phonesCategory.click();
        getPage().waitForLoadState();

        return this;
    }

    private Locator getNthProduct(int number) {

        return allProductsTitles.get(number);
    }

    private Locator getNthCardTitle(int number) {

        return allProductsTitles.get(number);
    }

    private void getRandomProduct() {
        getNthProduct(randomIndex).click();
    }

    @Step("Click random ProductTitle.")
    public ProdPage clickRandomCardTitle() {
        getNthCardTitle(randomIndex).click();
        getPage().waitForLoadState();

        return new ProdPage(getPage());
    }



}


// file:///C:/Users/laysa/OneDrive/Desktop/QA%20Books/Checklist_for_Testing_of_web_Application_1684852084.pdf