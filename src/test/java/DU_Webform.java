import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DU_Webform {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //implicit method
    }

    @DisplayName("Check if the title is correct")
    @Test
    public void visitUrl() {
        driver.get("https://www.digitalunite.com/practice-webform-learners/");
        String titleActual = driver.getTitle();
        String titleExpected = "Practice webform for learners | Digital Unite";
        Assertions.assertTrue(titleActual.equals(titleExpected));
    }

    @DisplayName("Fill up the form")
    @Test
    public void fillUpForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners/");

        WebElement acceptCookiesBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptCookiesBtn.click();
        Thread.sleep(3000); // Small delay for cookie modal to close

        List<WebElement> inputFields = driver.findElements(By.className("form-control"));

        // Fill the form fields
        inputFields.get(0).sendKeys("Human1");      // Name field (edit-name)
        inputFields.get(1).sendKeys("45");            // Number field (edit-number)

        // Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,300)");

        // Get all input fields with form-control class (includes textarea)
        List<WebElement> formControls = driver.findElements(By.cssSelector(".form-control"));

        // Fill the basic form fields
        formControls.get(2).sendKeys("04-18-2025");  // Date field
        formControls.get(3).sendKeys("raj@test.com"); // Email field
        formControls.get(4).sendKeys("I'm testing this software"); // Textarea

//        String filePath = "C:\\testfiles\\sample.pdf;
//        String filePath = "/src/test/resources/dummy.pdf";
        String filePath = System.getProperty("user.dir") + "/src/test/resources/dummy.pdf";


        WebElement fileInput = driver.findElement(By.id("edit-uploadocument-upload"));
        fileInput.sendKeys(filePath);
        Thread.sleep(3000);

        driver.findElement(By.id("edit-age")).click(); // checkbox
        driver.findElement(By.id("edit-submit")).click(); // submit button click
        String SubmissionTitle = driver.findElement(By.className("block-page-title-block")).getText();
        Assertions.assertTrue(SubmissionTitle.contains("Thank you for your submission!"));


    }

    @AfterAll
    public void closeDriver(){
        driver.quit();
    }

}
