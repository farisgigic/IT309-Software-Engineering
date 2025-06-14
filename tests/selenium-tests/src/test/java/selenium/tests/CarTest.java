package selenium.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {
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
    public void testValidCarAddition() throws InterruptedException {
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

        WebElement myCarsButton = driver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div/nav/ul/li[3]/a"));
        myCarsButton.click();
        Thread.sleep(2000);
        WebElement addCarButton = driver.findElement(By.xpath("/html/body/main/section[12]/div[2]/main/div/div[1]/div[3]/a/button"));
        addCarButton.click();
        Thread.sleep(1000);

        WebElement manufacturerField = driver.findElement(By.xpath("/html/body/main/section[6]/section/div/div/form/div[1]/input"));
        manufacturerField.click();

        WebElement manufacturerInput = driver.findElement(By.id("manufacturer"));
        manufacturerInput.sendKeys("Audi");

        WebElement modelField = driver.findElement(By.id("model"));
        modelField.sendKeys("A4");
        WebElement yearField = driver.findElement(By.id("year"));
        yearField.sendKeys("2020");
        WebElement mileageField = driver.findElement(By.id("mileage"));
        mileageField.sendKeys("15000");
        WebElement engineField = driver.findElement(By.id("engine"));
        engineField.sendKeys("2.0 TDI");

        Select fuelTypeField = new Select(driver.findElement(By.id("fuel_type")));
        fuelTypeField.selectByValue("diesel");

        Select transmissionField = new Select(driver.findElement(By.id("transmission")));
        transmissionField.selectByValue("manual");

        Select drivetrainField = new Select(driver.findElement(By.id("drivetrain")));
        drivetrainField.selectByValue("fwd");

        Select tiresField = new Select(driver.findElement(By.id("tires")));
        tiresField.selectByValue("summer");

        WebElement addButton = driver.findElement(By.xpath("/html/body/main/section[6]/section/div/div/form/div[7]/button"));
        addButton.click();
        Thread.sleep(1000);

        String expectedUrlAfterAddition = "https://software-engineering-46ifz.ondigitalocean.app/#cars";
        String actualUrlAfterAddition = driver.getCurrentUrl();
        assertTrue(actualUrlAfterAddition.equals(expectedUrlAfterAddition), "Redirection after successful registration failed.");
    }

    @Test
    public void testInvalidCarAddition() throws InterruptedException {
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

        WebElement myCarsButton = driver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div/nav/ul/li[3]/a"));
        myCarsButton.click();
        Thread.sleep(2000);
        WebElement addCarButton = driver.findElement(By.xpath("/html/body/main/section[12]/div[2]/main/div/div[1]/div[3]/a/button"));
        addCarButton.click();
        Thread.sleep(1000);

        WebElement addButton = driver.findElement(By.xpath("/html/body/main/section[6]/section/div/div/form/div[7]/button"));
        addButton.click();
        Thread.sleep(1000);

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[3]/div/div"));
        assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
    }
}
