import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;

public class FormTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void FormElements() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement subjectField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='subjectsInput']")));
        WebElement submitButton = driver.findElement(By.id("submit"));

        new Actions(driver).scrollToElement(subjectField).perform();
        driver.findElement(By.id("firstName")).sendKeys("Shakh");
        driver.findElement(By.id("lastName")).sendKeys("Rukh");
        driver.findElement(By.id("userEmail")).sendKeys("test@email.com");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

//        WebElement subjectsSelector = driver.findElement(By.id("subjectsContainer"));
//        subjectsSelector.sendKeys("Math");
//        subjectsSelector.sendKeys(Keys.ENTER);

        subjectField.sendKeys("Math");
        subjectField.sendKeys(Keys.TAB);
        subjectField.sendKeys("Arts");
        subjectField.sendKeys(Keys.TAB);
//

        new Actions(driver).scrollToElement(submitButton).perform();

        driver.findElement(By.xpath("//label[text()='Music']")).click();
        driver.findElement(By.id("currentAddress")).sendKeys("Test Address");


        WebElement stateSelect = driver.findElement(By.xpath("//input[@id='react-select-3-input']"));
        stateSelect.sendKeys("NCR", Keys.TAB);;

        WebElement citySelect = driver.findElement(By.xpath("//input[@id='react-select-4-input']"));
        citySelect.sendKeys("Delhi", Keys.TAB);


        new Actions(driver).scrollByAmount(0,submitButton.getSize().getHeight()).perform();
        submitButton.click();


        /// Verification
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));


        WebElement studentName = driver.findElement(By.xpath("//td[text()='Student Name']//following-sibling::td"));
        Assert.assertEquals(studentName.getText(), "Mario Hell");


        WebElement email = driver.findElement(By.xpath("//td[text()='Student Email']//following-sibling::td"));
        Assert.assertEquals(email.getText(), "test@email.com");


        WebElement gender = driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td"));
        Assert.assertEquals(gender.getText(), "Male");


        WebElement mobile = driver.findElement(By.xpath("//td[text()='Mobile']//following-sibling::td"));
        Assert.assertEquals(mobile.getText(), "1234567890");


        WebElement subjects = driver.findElement(By.xpath("//td[text()='Subjects']//following-sibling::td"));
        Assert.assertTrue(subjects.getText().contains("Math"));
        Assert.assertTrue(subjects.getText().contains("Arts"));


        WebElement hobbies = driver.findElement(By.xpath("//td[text()='Hobbies']//following-sibling::td"));
        Assert.assertEquals(hobbies.getText(), "Music");


        WebElement address = driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td"));
        Assert.assertEquals(address.getText(), "Test Address");


        WebElement stateCity = driver.findElement(By.xpath("//td[text()='State and City']//following-sibling::td"));
        Assert.assertEquals(stateCity.getText(), "NCR Delhi");

        System.out.printf("Assert Successful");
    }




    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
