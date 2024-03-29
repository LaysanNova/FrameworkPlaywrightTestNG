package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProdPage;
import tests.helpers.TestUtils;
import utils.reports.LoggerUtils;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProdTest extends BaseTest {

    @Test(testName = "TC.XXX.XX: Product View Display.")
    @Description("Objective: verify the appearance and accuracy of the product view on the webpage.")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductViewDisplay() {

        HomePage homePage = new HomePage(getPage());

        LoggerUtils.logInfo("randomProduct");
        final Locator randomProduct =
                homePage
                        .clickRandomCategory()
                        .getRandomProduct();

        LoggerUtils.logInfo("productData");
        final Map<String, String> productData = TestUtils.getData(randomProduct);

        LoggerUtils.logInfo("prodPage");
        ProdPage prodPage =
                homePage
                        .clickProductByLocator(randomProduct);

        LoggerUtils.logInfo("image");
        final Locator image = prodPage.getImg();

        LoggerUtils.logInfo("productBody");
        final Locator productBody = prodPage.getProductBody();

        Allure.step("Assert that image is visible.");
        assertThat(image).isVisible();

        Allure.step("Assert that image is valid.");
        Assert.assertEquals(productData.get("img"), image.getAttribute("src"));

        Allure.step("that the product title/name is correctly displayed and matches the intended product.");
        Assert.assertEquals(prodPage.getProductTitle(), productData.get("title"));
        Assert.assertEquals(prodPage.getProductPrice(), productData.get("price"));
        Assert.assertEquals(prodPage.getProductDesc(), productData.get("desc"));
    }
}
