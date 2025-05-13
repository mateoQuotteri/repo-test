import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class testgoogle {
    public WebDriver driver;

    @Test
    public void test_1(){
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        String title = driver.getTitle(); // "Google"
        System.out.println("Título: " + title);

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");

        // Captura el valor antes de enviar ENTER
        String searchValue = searchBox.getAttribute("value");
        System.out.println("Valor de búsqueda: " + searchValue);

        // Enviar la búsqueda
        searchBox.sendKeys(Keys.ENTER);

        // Esperar a que se cargue la página de resultados
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Selenium"));

        // Si necesitas acceder al campo de búsqueda en la página de resultados, debes localizarlo nuevamente
        WebElement searchBoxResults = driver.findElement(By.name("q"));
        String newValue = searchBoxResults.getAttribute("value");
        System.out.println("Valor en la página de resultados: " + newValue);

        driver.quit();
    }
}