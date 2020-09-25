package Tests;

import static org.junit.Assert.*;

import Suporte.Generator;
import Suporte.Screenshot;
import Suporte.Web;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

 @RunWith(DataDrivenTestRunner.class)
 @DataLoader(filePaths = "informacoesUsuarioTest.csv")

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();
    @Before
    public void setUp() {
        navegador = Web.createChrome();


        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulário de Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));


        //Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Clicar no campo com name "password" que está dentro do formulário de id "signinbox"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");


        //Clicar no link com texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com class "me" está o texto "Hi Julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Julio", textoNoElementoMe);


        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();


    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo") String tipo, @Param(name="contato") String contato, @Param(name="mensagem") String mensagemesperada) {




        //Clicar em um botão que possui através de seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar a popup onde está o formulário de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na comobo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("tipo");

        //No campo de name "contact" digitar "+551199999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("contato");

        //Clicar no link de teste "SAVE" que está no popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto "Your contact has been added!"//
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemesperada, mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario() {

        // Clicar no elemento pelo seu xpath //span[text()="+551133334444"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+551133334444\"]/following-sibling::a")).click();

        // Confirmar a janela Javascript
        navegador.switchTo().alert().accept();

        // Validar que a mensagem validada foi "Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);
        String screenshotArquivo = "C:\\Test report\\" + Generator.DataHoraParaArquivo() + test.getMethodName()+ ".png";
        Screenshot.tirar(navegador, screenshotArquivo );


        // Aguardar até 10 segundos, para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

   @After
    public void tierDown() {
        //Fechar o navegador
      //  navegador.quit();

    }
}

