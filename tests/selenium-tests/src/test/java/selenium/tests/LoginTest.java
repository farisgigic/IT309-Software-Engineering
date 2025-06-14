package selenium.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private static WebDriver driver;
    private static String baseUrl ;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/HP/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        baseUrl = "https://software-engineering-46ifz.ondigitalocean.app/login/index.html";
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        driver.get(baseUrl);

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/section/div/div/form/div[6]/button"));

        emailField.sendKeys("invalid@example.com");
        passwordField.sendKeys("wrongpassword");
        loginButton.click();

        Thread.sleep(1000);

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/div/div/div"));
        assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
        assertTrue(errorMessage.getText().contains("Invalid username or password"), "Error message does not contain expected text.");
    }

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        driver.get(baseUrl);

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/section/div/div/form/div[6]/button"));

        emailField.sendKeys("emina.omercevic@stu.ibu.edu.ba");
        passwordField.sendKeys("emina");
        loginButton.click();

        Thread.sleep(3000);

        String expectedUrl = "https://software-engineering-46ifz.ondigitalocean.app/#landing";
        String actualUrl = driver.getCurrentUrl();
        assertTrue(actualUrl.equals(expectedUrl), "Redirection after successful login failed.");
    }
}
