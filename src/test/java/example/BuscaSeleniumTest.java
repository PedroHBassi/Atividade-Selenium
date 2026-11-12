package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuscaSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void configurarDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void abrirNavegador() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    void devePesquisarSeleniumNaInternet() {
        driver.get("https://duckduckgo.com/");

        WebElement campoBusca = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q"))
        );

        campoBusca.sendKeys("Selenium WebDriver");
        campoBusca.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.titleContains("Selenium"));

        Assertions.assertTrue(
                driver.getTitle().toLowerCase().contains("selenium"),
                "O título da página deveria apresentar o termo Selenium."
        );
        Assertions.assertTrue(
                driver.getCurrentUrl().contains("duckduckgo.com"),
                "O navegador deveria permanecer no DuckDuckGo."
        );
    }

    @AfterEach
    void fecharNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
