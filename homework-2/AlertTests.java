import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void AlertElements() throws InterruptedException {
    driver.get("https://demo.automationtesting.in/Alerts.html");

    driver.findElement(By.xpath("//a[contains(text(),'Alert with Textbox')]")).click();

    driver.findElement(By.xpath("//button[@onclick='promptbox()']")).click();

    Alert alert = wait.until(ExpectedConditions.alertIsPresent());

    String name = "Mario Jovano";
    alert.sendKeys(name);

    alert.accept();

    WebElement resultText = driver.findElement(By.id("demo1"));
    Assert.assertEquals(resultText.getText(), "Hello " + name + " How are you today");
    System.out.println("Assert Successful");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
