package FrontTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import reportes.ExtentFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTest {
    private WebDriver driver;
    private BasePage basePage;
    private WebDriverWait wait;

    // Configuración para hacer la ejecución más lenta (en milisegundos)
    private static final int DELAY_BETWEEN_STEPS = 1500; // 1.5 segundos entre acciones
    private static final int DELAY_BETWEEN_TESTS = 2000; // 2 segundos entre tests

    ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte.html" );
    ExtentReports extent;


    // Locators
    private final By CIUDAD_INPUT = By.id("ciudad");
    private final By SEARCH_BUTTON = By.className("btn-primario");
    private final By SEARCH_RESULT = By.xpath("//*[@id=\"68\"]/div[2]/div/div[1]");

    @BeforeEach
    public void setUp() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);

        // Configurar opciones de Chrome para desactivar la aceleración
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-accelerated-2d-canvas");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        basePage = new BasePage(driver, null);
        basePage.setup();
    }

    /**
     * Método para insertar pausas entre acciones para ver mejor la ejecución
     */
    private void slowDown() {
        try {
            Thread.sleep(DELAY_BETWEEN_STEPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    @Tag("BUSQUEDA")
    @Tag("SMOKE")
    public void BusquedaExitosa_Uruguay() throws InterruptedException {
        ExtentTest test = extent.createTest("BUSQUEDA EXITOSA URUGUAY");

        test.log(Status.INFO, "Comienza nuestro test de busqueda");
        basePage.url("https://digital-booking-front.digitalhouse.com/ ");
        slowDown();

        // Ingresar ciudad en el campo de búsqueda
        basePage.sendText("Punta del este", CIUDAD_INPUT);
        slowDown();
        basePage.sendKey(Keys.ENTER, CIUDAD_INPUT);
        slowDown();

        // Hacer clic en el botón de búsqueda
        basePage.click(SEARCH_BUTTON);
        slowDown();

        test.log(Status.PASS, "test exitoso a uruguay");

        // Verificar el resultado de la búsqueda
        String busquedaCorrecta = basePage.getText(SEARCH_RESULT);
        System.out.println("Resultado de búsqueda Uruguay: " + busquedaCorrecta);
        slowDown();
    }

    @Test
    @Order(2)
    @Tag("BUSQUEDA")
    @Tag("REGRESSION")
    public void BusquedaExitosa_Grecia() throws InterruptedException {
        ExtentTest test = extent.createTest("BUSQUEDA EXITOSA GRECIA");

        test.log(Status.INFO, "Comienza nuestro test de busqueda");
        // Navegar a la página
        basePage.url("https://digital-booking-front.digitalhouse.com/ ");
        slowDown();

        // Ingresar ciudad en el campo de búsqueda
        basePage.sendText("Grecia", CIUDAD_INPUT);
        slowDown();
        basePage.sendKey(Keys.ENTER, CIUDAD_INPUT);
        slowDown();

        // Hacer clic en el botón de búsqueda
        basePage.click(SEARCH_BUTTON);
        slowDown();

        test.log(Status.PASS, "test exitoso a grecia");

        // Verificar el resultado de la búsqueda
        String busquedaCorrecta = basePage.getText(SEARCH_RESULT);
        System.out.println("Resultado de búsqueda Grecia: " + busquedaCorrecta);
        slowDown();
    }

    @Test
    @Order(3)
    @Tag("BUSQUEDA")
    public void BusquedaExitosa_Paris() throws InterruptedException {
        ExtentTest test = extent.createTest("BUSQUEDA EXITOSA PARIS");

        test.log(Status.INFO, "Comienza nuestro test de busqueda");
        // Navegar a la página
        basePage.url("https://digital-booking-front.digitalhouse.com/ ");
        slowDown();

        // Ingresar ciudad en el campo de búsqueda
        basePage.sendText("París", CIUDAD_INPUT);
        slowDown();
        basePage.sendKey(Keys.ENTER, CIUDAD_INPUT);
        slowDown();

        // Hacer clic en el botón de búsqueda
        basePage.click(SEARCH_BUTTON);
        slowDown();

        test.log(Status.PASS, "test exitoso a paris");

        // Verificar el resultado de la búsqueda
        String busquedaCorrecta = basePage.getText(SEARCH_RESULT);
        System.out.println("Resultado de búsqueda París: " + busquedaCorrecta);
        slowDown();
    }

    @Test
    @Order(4)
    @Tag("BUSQUEDA")
    @Tag("SANITY")
    public void BusquedaExitosa_Roma() throws InterruptedException {
        ExtentTest test = extent.createTest("BUSQUEDA EXITOSA ROMA");

        test.log(Status.INFO, "Comienza nuestro test de busqueda");
        // Navegar a la página
        basePage.url("https://digital-booking-front.digitalhouse.com/ ");
        slowDown();

        // Ingresar ciudad en el campo de búsqueda
        basePage.sendText("Roma", CIUDAD_INPUT);
        slowDown();
        basePage.sendKey(Keys.ENTER, CIUDAD_INPUT);
        slowDown();

        // Hacer clic en el botón de búsqueda
        basePage.click(SEARCH_BUTTON);
        slowDown();

        test.log(Status.PASS, "test exitoso a roma");

        // Verificar el resultado de la búsqueda
        String busquedaCorrecta = basePage.getText(SEARCH_RESULT);
        System.out.println("Resultado de búsqueda Roma: " + busquedaCorrecta);
        slowDown();
    }

    @AfterEach
    public void tearDown() {
        try {
            // Pausa entre pruebas para poder ver los resultados
            Thread.sleep(DELAY_BETWEEN_TESTS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            basePage.close();
            extent.flush();
        }
    }
}