package pages;

import com.microsoft.playwright.Page;
import pages.model.Footer;

public class CartPage extends Footer<CartPage> {


    //page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("New message")).click();

    public CartPage(Page page) {

        super(page);
    }
}
