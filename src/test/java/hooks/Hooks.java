package hooks;

import com.microsoft.playwright.Page;
import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {
    private final TestContext ctx;
    private int stepNumber = 0;

    public Hooks(TestContext ctx) {
        this.ctx = ctx;
    }

    @Before
    public void setUp() {
        stepNumber = 0;
        ctx.start();
    }

    @AfterStep
    public void screenshotPorPaso(Scenario scenario) throws Exception {
        Page page = ctx.getPage();
        if (page == null) return;

        stepNumber++;
        byte[] png = page.screenshot();

        // 1) Se adjunta justo debajo del step en el reporte HTML
        scenario.attach(png, "image/png", "Paso " + stepNumber);

        // 2) Se guarda en disco: una carpeta por escenario
        String escenario = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        Path dir = Paths.get("target/screenshots", escenario);
        Files.createDirectories(dir);
        String estado = scenario.isFailed() ? "FAILED" : "OK";
        Files.write(dir.resolve(String.format("paso_%02d_%s.png", stepNumber, estado)), png);
    }

    @After
    public void tearDown(Scenario scenario) {
        String escenario = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        ctx.stop(escenario);
    }
}