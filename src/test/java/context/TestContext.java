package context;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class TestContext {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public void start() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("target/videos"))
                .setViewportSize(1280, 720));
        page = context.newPage();
        page.setDefaultTimeout(30000);
    }

    public void stop(String escenario) {
        if (context != null) context.close();   // aquí se guarda el video
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public Page getPage() {
        return page;
    }
}