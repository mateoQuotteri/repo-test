import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest {
    public WebDriver driver;

    @Test
    public void BusquedaExitosa() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://digital-booking-front.digitalhouse.com/");
        Thread.sleep(1000);

        WebElement searchBox = driver.findElement(By.id("ciudad"));
        searchBox.sendKeys("Punta del este");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        WebElement searchButton = driver.findElement(By.className("btn-primario"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement searchOk = driver.findElement(By.xpath("//*[@id=\"68\"]/div[2]/div/div[1]"));
        String busquedaCorrecta = searchOk.getText();
        System.out.println(busquedaCorrecta);

        driver.quit();
    }
    // Añadimos el método main para ejecutar la prueba
    public static void main(String[] args) {
        try {
            SearchTest test = new SearchTest();
            test.BusquedaExitosa();
            System.out.println("Prueba completada con éxito");
        } catch (InterruptedException e) {
            System.err.println("La prueba fue interrumpida: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error durante la ejecución de la prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}