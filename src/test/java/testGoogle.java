import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testGoogle {
    public WebDriver driver;

    @Test
    public void test_1(){
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.getTitle(); // "Google"


        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));


        searchBox.sendKeys(searchBox.getText());
        searchButton.click();

        searchBox.getAttribute("value");

        driver.quit();



    }

}
