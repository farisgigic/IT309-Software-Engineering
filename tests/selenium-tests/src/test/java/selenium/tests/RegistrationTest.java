package selenium.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
    private static WebDriver driver;
    private static String baseUrl ;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/HP/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        baseUrl = "https://software-engineering-46ifz.ondigitalocean.app/login/signup.html";
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testInvalidRegistration() throws InterruptedException {
        driver.get(baseUrl);

        WebElement registerButton = driver.findElement(By.xpath("/html/body/section/div/div/form/button[1]"));

        registerButton.click();

        Thread.sleep(1000);

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/section/div/div/form/div[2]/label[2]"));
        assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
        assertTrue(errorMessage.getText().contains("This field is required"), "Error message does not contain expected text.");
    }

    @Test
    public void testSuccessfulRegistration() throws InterruptedException {
        driver.get(baseUrl);

        WebElement firstNameField = driver.findElement(By.id("first_name"));
        WebElement lastNameField = driver.findElement(By.id("last_name"));
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement registerButton = driver.findElement(By.xpath("/html/body/section/div/div/form/button[1]"));

        firstNameField.sendKeys("John");
        lastNameField.sendKeys("Doe");
        usernameField.sendKeys("johndoe123");
        emailField.sendKeys("johndoe12345@example.com");
        passwordField.sendKeys("securePassword1!");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('birth_date').value = '2000-02-22';");
        js.executeScript("document.getElementById('birth_date').dispatchEvent(new Event('change'));");
        registerButton.click();

        Thread.sleep(3000);

        String expectedUrl = "https://software-engineering-46ifz.ondigitalocean.app/#landing";
        String actualUrl = driver.getCurrentUrl();
        assertTrue(actualUrl.equals(expectedUrl), "Redirection after successful registration failed.");
    }
}
