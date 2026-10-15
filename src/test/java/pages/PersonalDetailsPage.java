package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PersonalDetailsPage extends BasePage {
    private final Locator firstNameInput;
    private final Locator lastNameInput;

    public PersonalDetailsPage(Page page) {
        super(page);
        firstNameInput = page.locator("input[name='firstName']");
        lastNameInput  = page.locator("input[name='lastName']");
    }

    public PersonalDetailsPage waitUntilLoaded() {
        page.waitForURL("**/pim/viewPersonalDetails/**");
        return this;
    }

    public Locator firstName() {
        return firstNameInput;
    }

    public Locator lastName() {
        return lastNameInput;
    }
}