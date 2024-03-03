package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.model.HeadMenu;

import java.util.List;

public class PreconditionPage extends HeadMenu<PreconditionPage>  {

    public PreconditionPage(Page page) {
        super(page);
    }

    public void cleanCart() {

        final CartPage cartPage =
                new HomePage(getPage())
                        .clickCartMenu();

        List<Locator> delete = cartPage.getDeleteButtons();
        for (Locator d : delete) {
            cartPage.clickFirstDelete();
        }

        cartPage
                .clickHomeMenu();

    }
}
