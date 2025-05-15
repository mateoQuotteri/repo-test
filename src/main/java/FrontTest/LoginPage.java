package FrontTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage {

    private By mail = By.name("log");
    private By password = By.id("user_pass");
    private By login = By.id("wp-submit");
    private By hello = By.id("backtoblog"); // Para verificar algún texto tras el login
    private By name = By.className("wp-login-logo"); // Elemento logo, ajustarlo a lo que necesites verificar
    private By mailReq = By.xpath("//p[@id='login_error']"); // WordPress suele mostrar errores en un elemento con ID login_error
    private By passwordReq = By.xpath("//p[@id='login_error']");
    private By mailInv = By.xpath("//p[@id='login_error'][contains(text(),'nombre de usuario')]");
    private By passwordShort = By.xpath("//p[@id='login_error'][contains(text(),'contraseña')]");
    private By credencials = By.id("login_error");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void escribirCorreo(String correo) throws InterruptedException{
        this.sendText(correo, mail);
    }

    public void escribirContrasenia(String contraseña) throws InterruptedException{
        this.sendText(contraseña, password);
    }

    public void clickLogin() throws InterruptedException {
        this.click(login);
    }

    public String saludo() throws InterruptedException {
        System.out.println("MENSAJE DE SALUDO: " + this.getText(hello));
        return this.getText(hello);
    }

    public String loginNombre() throws InterruptedException {
        System.out.println("SE VERIFICA EL NOMBRE DEL LOGIN: " + this.getText(name));
        return this.getText(name);
    }

    public String correoObligatorio() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(mailReq));
        return this.getText(mailReq);
    }

    public String contraseñaObligatoria() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordReq));
        return this.getText(passwordReq);
    }

    public String correoInvalido() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(mailInv));
        return this.getText(mailInv);
    }

    public String contraseñaCorta() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordShort));
        return this.getText(passwordShort);
    }

    public String credencialesInvalidas() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(credencials));
        return this.getText(credencials);
    }
}