package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class LoginSteps {
    private final TestContext ctx;

    public LoginSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @Given("el administrador ha ingresado al sistema")
    public void elAdministradorHaIngresadoAlSistema() {
        new LoginPage(ctx.getPage())
                .navigate()
                .loginAs("Admin", "admin123")
                .waitUntilLoaded();
    }
}