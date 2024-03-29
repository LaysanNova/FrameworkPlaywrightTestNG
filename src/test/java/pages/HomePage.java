package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pages.model.Footer;
import pages.model.HeadMenu;
import tests.helpers.TestData;

import java.util.List;

public class HomePage extends HeadMenu<HomePage> implements IRandom {

    private final  List<Locator> allCategories = locator("a#itemc").all();
    private final Locator phonesCategory = link("Phones");
    private final Locator laptopsCategory = link("Laptops");
    private final Locator monitorsCategory = link("Monitors");
    private final Locator previousButton = locator("button[id='prev2']");
    private final Locator nextButton = locator("button[id='next2']");
    private final Footer footer = new Footer();

    private final int randomNumber = getRandomInt(TestData.maxProductsOnPage);

    public HomePage(Page page) {
        super(page);
    }

    public HomePage clickPhonesCategory() {
        phonesCategory.click();
        getPage().waitForLoadState();

        return this;
    }

    public HomePage clickLaptopsCategory() {
        laptopsCategory.click();

        return this;
    }

    public HomePage clickMonitorsCategory() {
        monitorsCategory.click();

        return this;
    }

    @Step("Click category {category}.")
    public HomePage clickCategory(String category) {

        switch (category) {
            case "Phones" -> clickPhonesCategory();
            case "Laptops" -> clickLaptopsCategory();
            case "Monitors" -> clickMonitorsCategory();
        }

        return this;
    }

    @Step("Click random category.")
    public HomePage clickRandomCategory() {

        Locator randomCategory = getRandomValue(allCategories);
        randomCategory.click();

        return this;
    }

    @Step("Get Products List displayed on the page.")
    public List<Locator> getProductsList() {

        getPage().waitForTimeout(2000);

        return allElements("#tbodyid > div");
    }

    public Locator getRandomProduct() {
        List<Locator> allProducts = getProductsList();

        return getRandomValue(allProducts);
    }

    @Step("Click random product.")
    public ProdPage clickRandomProduct() {

        Locator randomProduct = getRandomProduct();
        randomProduct.locator("h4.card-title > a").click();

        return new ProdPage(getPage());
    }

    @Step("Click product by Locator.")
    public ProdPage clickProductByLocator(Locator product) {

        product.click();

        return new ProdPage(getPage());
    }

    @Step("Click product by Number {randomNumber}.")
    public ProdPage clickProductByNumber(int randomNumber) {

        List<Locator> productList = getProductsList();
        Locator item  = productList.get(randomNumber);

        getPage().waitForTimeout(2000);
        item.click();

        return new ProdPage(getPage());
    }

    @Step("Get random Number.")
    public int getRandomNumber() {

       return randomNumber;
    }

    @Step("Click 'Next' Button.")
    public HomePage clickNextButton() {

        nextButton.click();
        getPage().waitForTimeout(1000);

        return this;
    }
    @Step("Click 'Previous' Button.")
    public HomePage clickPreviousButton() {

        previousButton.click();
        getPage().waitForTimeout(1000);

        return this;
    }
}
