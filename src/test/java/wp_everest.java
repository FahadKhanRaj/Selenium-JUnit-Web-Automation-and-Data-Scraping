import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class wp_everest {
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
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        String titleActual = driver.getTitle();
        String titleExpected = "Guest Registration Form – User Registration";
        Assertions.assertTrue(titleActual.equals(titleExpected));
    }

    @DisplayName("Fill up the form")
    @Test
    public void fillUpForm()  throws InterruptedException{
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        driver.findElement(By.id("first_name")).sendKeys("First Name Guy");
        driver.findElement(By.id("last_name")).sendKeys("Last Name Guy");
        driver.findElement(By.id("user_email")).sendKeys("raj2@me.com");
        driver.findElement(By.id("radio_1665627729_Male")).click();
        driver.findElement(By.id("user_pass")).sendKeys("PassWord123!213apowkdgWIJ");

        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dateField = driver.findElement(By.cssSelector("input[data-id='date_box_1665628538']"));
        js.executeScript("arguments[0].removeAttribute('readonly')", dateField);
        dateField.clear();
        dateField.sendKeys("1990-12-12");

        js.executeScript("window.scrollBy(0,500)");
//        Thread.sleep(2000);

        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");

//        driver.findElement(By.id("phone_1665627880")).click();
//        driver.findElement(By.id("phone_1665627880")).sendKeys("6562788046");
// full xpath to catch the phone number field and enter.
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/main/article/div/div/div/form/div[2]/div[1]/div[2]/p/input"))
                .sendKeys("6562788046");
//emergency number
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/main/article/div/div/div/form/div[2]/div[1]/div[3]/p/input"))
                .sendKeys("1112788046");


        WebElement dateField2 = driver.findElement(By.cssSelector("input[data-id='date_box_1665629845']"));
        js.executeScript("arguments[0].removeAttribute('readonly')", dateField2);
        dateField2.clear();
        dateField2.sendKeys("2025-02-15");

        WebElement countryDropdown = driver.findElement(By.id("country_1665629257"));
        Select select = new Select(countryDropdown);
        select.selectByVisibleText("Bangladesh");

        driver.findElement(By.id("number_box_1665629930")).sendKeys("7"); //length of stay
        driver.findElement(By.id("input_box_1665630010")).sendKeys("ROOM20BED5"); //room number

        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(1000);

        driver.findElement(By.id("textarea_1665630078")).sendKeys("Engineer at Dhaka"); //room number
        driver.findElement(By.id("radio_1665627931_Yes")).click();
        driver.findElement(By.id("radio_1665627997_Shared Room")).click();
        driver.findElement(By.id("radio_1665628131_Lactose Allergy")).click();

        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(1000);

        WebElement activityDropdown = driver.findElement(By.id("select_1665628361"));
        Select selecting = new Select(activityDropdown);
        selecting.selectByVisibleText("Luncheon");

        driver.findElement(By.id("privacy_policy_1665633140")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"user-registration-form-771\"]/form/div[4]/button")).click();
        Thread.sleep(2000);

        String successMessage = driver.findElement(By.xpath("//ul[contains(text(), 'User successfully registered.')]")).getText();
        Assertions.assertTrue(successMessage.contains("User successfully registered."));



    }

    @AfterAll
    public void closeDriver(){
        driver.quit();
    }

}
