package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddEmployeePage extends BasePage {
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator saveButton;

    public AddEmployeePage(Page page) {
        super(page);
        firstNameInput = page.locator("input[name='firstName']");
        lastNameInput  = page.locator("input[name='lastName']");
        saveButton     = page.locator("button[type='submit']");
    }

    public AddEmployeePage waitUntilLoaded() {
        page.waitForURL("**/pim/addEmployee");
        return this;
    }

    public PersonalDetailsPage registerEmployee(String firstName, String lastName) {
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        saveButton.click();
        return new PersonalDetailsPage(page);
    }
}
