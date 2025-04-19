import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.io.IOException;
import java.util.Set;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class webScrap_DSE {
            WebDriver driver;

            @BeforeAll
            public void setup() {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            }

            @Test
            @DisplayName("For Checking whether the table data are in the text file or not")

            public void Scrape_DSE_Table() throws IOException {
                driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");

                List<WebElement> table = driver.findElements(By.tagName("tbody"));
                FileWriter writer = new FileWriter("./src/test/resources/dseScrap.txt", true);

                // Store them into a Set because Array does not store unique.

                Set<String> row = new HashSet<>();

                for (WebElement tablebody : table) {
                    List<WebElement> rowElem = tablebody.findElements(By.tagName("tr"));

                    for (WebElement singleRow : rowElem) {

                        List<WebElement> rowCells = singleRow.findElements(By.tagName("td"));
                        StringBuilder rowBuilder = new StringBuilder();

                        for (WebElement cell : rowCells) {
                            String cellTxt = cell.getText();
                            rowBuilder.append(cellTxt).append("\t");
                        }

                        String rowString = rowBuilder.toString().trim();

                        // filtering
                        if (rowString.matches("^\\d+\\s.*") && row.add(rowString)) {

                            writer.write(rowString + "\n");
                            System.out.println(rowString);
                        }
                    }
                }
                writer.flush();
                writer.close();
            }
        }