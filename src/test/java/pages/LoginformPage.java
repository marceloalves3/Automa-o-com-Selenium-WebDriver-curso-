package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginformPage extends BasePage {


    public LoginformPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginformPage typeLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);

        return this;

    }

    public LoginformPage typePassword(String password) {
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);

        return this;

    }
    public SecretaPage clicarSignIn() {

        navegador.findElement(By.linkText("SIGN IN")).click();
        return new SecretaPage(navegador);
    }
    public  SecretaPage fazerLogin(String login, String password) {
        typeLogin(login);
        typePassword(password);
        clicarSignIn();
        return new SecretaPage(navegador);





    }

}
