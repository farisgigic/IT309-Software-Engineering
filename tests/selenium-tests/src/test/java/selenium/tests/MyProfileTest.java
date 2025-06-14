package selenium.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyProfileTest {
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
    public void testValidProfileEdit() throws InterruptedException {
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

        WebElement dropdownToggle = driver.findElement(By.id("userDropdown"));
        dropdownToggle.click();

        Thread.sleep(500);

        WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
        myProfileLink.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("#myprofile"), "Did not navigate to My Profile section.");

        Thread.sleep(2000);

        WebElement editProfileButton = driver.findElement(By.xpath("/html/body/main/section[14]/section/div/div/div/div/div/div[2]/div/div[2]/button"));
        editProfileButton.click();
        Thread.sleep(1000);

        WebElement firstNameField = driver.findElement(By.id("editFirstName"));
        WebElement lastNameField = driver.findElement(By.id("editLastName")); 
        WebElement usernameField = driver.findElement(By.id("editUsername"));
        WebElement emailInputField = driver.findElement(By.id("editEmail"));
        WebElement cityFiels = driver.findElement(By.id("editCity"));

        firstNameField.clear();
        firstNameField.sendKeys("Emina");
        lastNameField.clear();
        lastNameField.sendKeys("Omercevic");
        usernameField.clear();
        usernameField.sendKeys("eminaomercevic");
        emailInputField.clear();
        emailInputField.sendKeys("emina.omercevic@stu.ibu.edu.ba");
        cityFiels.clear();
        cityFiels.sendKeys("Cazin");	

        WebElement saveChangesButton = driver.findElement(By.xpath("/html/body/main/section[14]/div[2]/div/form/div[6]/button[2]"));
        saveChangesButton.click();
        Thread.sleep(2000);

        assertEquals("Emina Omercevic", driver.findElement(By.id("full_name_detail")).getText());
        assertEquals("eminaomercevic", driver.findElement(By.id("username")).getText());
        assertEquals("emina.omercevic@stu.ibu.edu.ba", driver.findElement(By.id("email_detail")).getText());
        assertEquals("Cazin", driver.findElement(By.id("city")).getText());
    }

    @Test
public void testInvalidProfileEdit() throws InterruptedException {
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

    WebElement dropdownToggle = driver.findElement(By.id("userDropdown"));
    dropdownToggle.click();

    Thread.sleep(500);

    WebElement myProfileLink = driver.findElement(By.linkText("My Profile"));
    myProfileLink.click();

    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("#myprofile"), "Did not navigate to My Profile section.");

    Thread.sleep(2000);

    WebElement editProfileButton = driver.findElement(By.xpath("/html/body/main/section[14]/section/div/div/div/div/div/div[2]/div/div[2]/button"));
    editProfileButton.click();

    Thread.sleep(1000);

    WebElement firstNameField = driver.findElement(By.id("editFirstName"));
    WebElement lastNameField = driver.findElement(By.id("editLastName")); 
    WebElement usernameField = driver.findElement(By.id("editUsername"));
    WebElement emailInputField = driver.findElement(By.id("editEmail"));
    WebElement cityFiels = driver.findElement(By.id("editCity"));

    firstNameField.clear();
    lastNameField.clear();
    usernameField.clear();
    emailInputField.clear();
    cityFiels.clear();

    WebElement saveChangesButton = driver.findElement(By.xpath("/html/body/main/section[14]/div[2]/div/form/div[6]/button[2]"));
    saveChangesButton.click();

    Thread.sleep(2000);

    WebElement cancelButton = driver.findElement(By.xpath("/html/body/main/section[14]/div[2]/div/form/div[6]/button[1]"));
    cancelButton.click();

    assertEquals("Emina Omercevic", driver.findElement(By.id("full_name_detail")).getText(), "Name should not change when fields are empty.");
    assertEquals("eminaomercevic", driver.findElement(By.id("username")).getText(), "Username should not change when fields are empty.");
    assertEquals("emina.omercevic@stu.ibu.edu.ba", driver.findElement(By.id("email_detail")).getText(), "Email should not change when fields are empty.");
    assertEquals("Cazin", driver.findElement(By.id("city")).getText(), "City should not change when fields are empty.");
    }
}
