package tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ContactModal;
import pages.HomePage;
import tests.helpers.TestData;
import tests.helpers.TestUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactTest extends BaseTest{
    @Test(testName = "TC.XXX.XX: Contact Us Functionality.")
    @Description("Verify that all fields are clear and accessible for user input.")
    @Severity(SeverityLevel.NORMAL)
    public void testContactPopUp() {

        ContactModal contactModal = new HomePage(getPage())
                .clickContactMenu();

        Locator modalTitle = contactModal.getModalTitle();
        Locator modalHeader = contactModal.getModalHeader().nth(0);
        Locator modalBody = contactModal.getModalBody().nth(0);
        Locator modalFooter = contactModal.getModalFooter().nth(0);

        assertThat(modalTitle).isVisible();
        assertThat(modalTitle).hasText(TestData.TITLE_CONTACT);

        Allure.step("Ensure that the modal 'Contact' contains all the necessary context.");
        Assert.assertEquals(TestUtils.getLocatorContext(modalHeader), TestData.contactModalHeader);
        Assert.assertEquals(TestUtils.getLocatorContext(modalBody), TestData.contactModalBody);
        Assert.assertEquals(TestUtils.getLocatorContext(modalFooter), TestData.contactModalFooter);
        assertThat(contactModal.getContactEmailInput()).isVisible();
        assertThat(contactModal.getContactNameInput()).isVisible();
        assertThat(contactModal.getContactMessageInput()).isVisible();

    }
}
