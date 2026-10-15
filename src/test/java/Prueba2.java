import com.microsoft.playwright.*;

public class Prueba2 {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // 1. Navegar al sitio
            page.navigate("https://opensource-demo.orangehrmlive.com/");
            System.out.println("Título: " + page.title());

            // 2. Ingresar credenciales
            page.locator("input[name='username']").fill("Admin");
            page.locator("input[name='password']").fill("admin123");

            // 3. Hacer clic en el botón de inicio de sesión
            page.locator("button[type='submit']").click();

            // 4. Esperar a que cargue el dashboard
            page.waitForURL("**/dashboard/index");
            System.out.println("¡Inicio de sesión exitoso! Nuevo título: " + page.title());

            // --- PASO ADICIONAL: Clic en el menú PIM ---
            // Usamos un selector por texto exacto para buscar la opción "PIM" en el menú lateral
            page.getByText("PIM", new Page.GetByTextOptions().setExact(true)).click();

            // Opcional: Esperar a que la URL del módulo PIM cargue para confirmar la navegación
            page.waitForURL("**/pim/viewEmployeeList");
            System.out.println("¡Navegación al módulo PIM exitosa!");

            // Pausa breve para observar el resultado antes de cerrar
            page.waitForTimeout(3000);

            // Es recomendable cerrar el navegador para liberar recursos
            browser.close();
        }
    }
}