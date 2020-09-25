package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecretaPage extends BasePage {

    public SecretaPage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarMe() {
        navegador.findElement(By.className("me"));

        return new MePage(navegador);

    }
}
