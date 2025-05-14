import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest {
    private WebDriver driver;
    private BasePage basePage;

    // Locators
    private final By CIUDAD_INPUT = By.id("ciudad");
    private final By SEARCH_BUTTON = By.className("btn-primario");
    private final By SEARCH_RESULT = By.xpath("//*[@id=\"68\"]/div[2]/div/div[1]");

    public void setUp() {
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        basePage.setup();
    }

    public void busquedaExitosa() throws InterruptedException {
        // Navegar a la página
        basePage.url("https://digital-booking-front.digitalhouse.com/");
        Thread.sleep(1000);

        // Ingresar ciudad en el campo de búsqueda
        basePage.sendText("Punta del este", CIUDAD_INPUT);
        basePage.sendKey(Keys.ENTER, CIUDAD_INPUT);
        Thread.sleep(1000);

        // Hacer clic en el botón de búsqueda
        basePage.click(SEARCH_BUTTON);
        Thread.sleep(1000);

        // Verificar el resultado de la búsqueda
        String busquedaCorrecta = basePage.getText(SEARCH_RESULT);
        System.out.println(busquedaCorrecta);
    }

    public void tearDown() {
        if (driver != null) {
            basePage.close();
        }
    }

    // Método main para ejecutar la prueba
    public static void main(String[] args) {
        SearchTest test = new SearchTest();
        try {
            test.setUp();
            test.busquedaExitosa();
            System.out.println("Prueba completada con éxito");
        } catch (InterruptedException e) {
            System.err.println("La prueba fue interrumpida: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error durante la ejecución de la prueba: " + e.getMessage());
            e.printStackTrace();
        } finally {
            test.tearDown();
        }
    }
}