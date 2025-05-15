package FrontTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeEach
    public void setUp() {
        // Configurar el ChromeDriver
        ChromeOptions options = new ChromeOptions();
        // Opcional: ejecutar en modo headless
        // options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Configurar el WebDriverWait con 10 segundos de timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Inicializar la página de login
        loginPage = new LoginPage(driver, wait);

        // Configurar los reportes
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/login-report.html");
        extent.attachReporter(spark);

        // Navegar a la página de login
        driver.get("https://tu-sitio-web.com/login"); // Reemplaza con tu URL real
    }

    @AfterEach
    public void tearDown() {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }

        // Finalizar el reporte
        extent.flush();
    }

    @Test
    @Order(1)
    @Tag("login")
    public void testLoginExitoso() throws InterruptedException {
        test = extent.createTest("Test de Login Exitoso");
        test.log(Status.INFO, "Iniciando prueba de login exitoso");

        loginPage.escribirCorreo("usuario@ejemplo.com");
        loginPage.escribirContrasenia("contraseña123");
        loginPage.clickLogin();

        // Verificar que el login fue exitoso
        assertEquals("← Ir a Negocios y Marketing", loginPage.saludo(), "El mensaje de saludo no coincide");

        test.log(Status.PASS, "Login exitoso verificado correctamente");
    }

    @Test
    @Order(2)
    @Tag("validacion")
    public void testCamposObligatorios() throws InterruptedException {
        test = extent.createTest("Test de Campos Obligatorios");
        test.log(Status.INFO, "Iniciando prueba de validación de campos obligatorios");

        // Intentar login sin ingresar datos
        loginPage.clickLogin();

        // Verificar mensajes de error
        assertEquals("El email es obligatorio", loginPage.correoObligatorio(), "El mensaje de correo obligatorio no coincide");
        assertEquals("La contraseña es obligatoria", loginPage.contraseñaObligatoria(), "El mensaje de contraseña obligatoria no coincide");

        test.log(Status.PASS, "Validación de campos obligatorios verificada correctamente");
    }

    @Test
    @Order(3)
    @Tag("validacion")
    public void testFormatoCorreoInvalido() throws InterruptedException {
        test = extent.createTest("Test de Formato de Correo Inválido");
        test.log(Status.INFO, "Iniciando prueba de validación de formato de correo");

        // Ingresar un correo con formato inválido
        loginPage.escribirCorreo("correo-invalido");
        loginPage.escribirContrasenia("contraseña123");
        loginPage.clickLogin();

        // Verificar mensaje de error
        assertEquals("El email es inválido", loginPage.correoInvalido(), "El mensaje de correo inválido no coincide");

        test.log(Status.PASS, "Validación de formato de correo verificada correctamente");
    }

    @Test
    @Order(4)
    @Tag("validacion")
    public void testContraseniaaCorta() throws InterruptedException {
        test = extent.createTest("Test de Contraseña Corta");
        test.log(Status.INFO, "Iniciando prueba de validación de longitud de contraseña");

        // Ingresar una contraseña corta
        loginPage.escribirCorreo("usuario@ejemplo.com");
        loginPage.escribirContrasenia("123");
        loginPage.clickLogin();

        // Verificar mensaje de error
        assertEquals("La contraseña debe tener más de 6 caracteres", loginPage.contraseñaCorta(), "El mensaje de contraseña corta no coincide");

        test.log(Status.PASS, "Validación de longitud de contraseña verificada correctamente");
    }

    @Test
    @Order(5)
    @Tag("login")
    public void testCredencialesInvalidas() throws InterruptedException {
        test = extent.createTest("Test de Credenciales Inválidas");
        test.log(Status.INFO, "Iniciando prueba de credenciales inválidas");

        // Ingresar credenciales incorrectas
        loginPage.escribirCorreo("usuario@ejemplo.com");
        loginPage.escribirContrasenia("contraseñaIncorrecta");
        loginPage.clickLogin();

        // Verificar mensaje de error
        assertTrue(loginPage.credencialesInvalidas().contains("Credenciales inválidas"), "El mensaje de credenciales inválidas no coincide");

        test.log(Status.PASS, "Validación de credenciales inválidas verificada correctamente");
    }
}