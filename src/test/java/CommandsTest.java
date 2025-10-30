import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains a Selenium test that performs a series of commands
 * on the-internet.herokuapp.com.
 */
public class CommandsTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Set up the WebDriver before each test
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        // Quit the WebDriver after each test to clean up resources
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void executeAllCommandsTest() {
        // 1. & 2. Open Chrome and navigate to the dynamic controls page
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        // --- DYNAMIC CONTROLS PAGE ACTIONS ---

        // 3. Click the “Enable” button.
        // We find the button inside the form with id 'input-example'
        WebElement enableButton = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        enableButton.click();

        // 4. Check that the input field is enabled and that the text "It's enabled!" is visible.
        // Use WebDriverWait to handle the asynchronous operation.
        // This waits for a maximum of 10 seconds for the message to become visible.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

        // Now that the wait is over, we can find the input field and check its state
        WebElement inputField = driver.findElement(By.xpath("//form[@id='input-example']/input"));

        // Assert that the input is enabled and the message text is correct
        assertTrue(inputField.isEnabled(), "Input field should be enabled.");
        assertEquals("It's enabled!", message.getText(), "The confirmation message is incorrect.");

        // → Print a message like “Input field enabled and text visible”
        System.out.println("Input field enabled and text visible");

        // 5. Check that the button text has changed from “Enable” to “Disable”.
        // The button element reference is still 'enableButton'. We check its current text.
        assertEquals("Disable", enableButton.getText(), "Button text did not change to 'Disable'.");

        // → Print a message like “Button text changed successfully”
        System.out.println("Button text changed successfully");

        // 6. Enter "Bootcamp" in the input field and then clear it.
        inputField.sendKeys("Bootcamp");
        // We can assert the value was entered
        assertEquals("Bootcamp", inputField.getAttribute("value"));
        inputField.clear();
        // And assert it was cleared
        assertEquals("", inputField.getAttribute("value"));

        // --- DRAG AND DROP PAGE ACTIONS ---

        // 7. Navigate to http://the-internet.herokuapp.com/drag_and_drop.
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");

        // 8. Check that columns A and B have the same Y coordinate.
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));

        // getLocation() returns a Point object with x and y coordinates
        Point locationA = columnA.getLocation();
        Point locationB = columnB.getLocation();

        // Assert that the Y coordinates are equal
        assertEquals(locationA.getY(), locationB.getY(), "Columns A and B are not aligned on the Y-axis.");

        // → Print a message like “Columns A and B aligned successfully”
        System.out.println("Columns A and B aligned successfully");
    }
}