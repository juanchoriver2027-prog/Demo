package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage extends BasePage {
    private final Locator pimMenu;

    public DashboardPage(Page page) {
        super(page);
        pimMenu = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("PIM").setExact(true));
    }

    public DashboardPage waitUntilLoaded() {
        page.waitForURL("**/dashboard/index");
        return this;
    }

    public PimPage goToPim() {
        pimMenu.click();
        return new PimPage(page);
    }
}