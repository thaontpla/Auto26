package feature.day06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import untils.ExcelUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BookVYourDemoHereTest {

    WebDriver driver;
    WebDriverWait wait;
    List<Map<String, String>> excelData;

    @BeforeTest
    public void setUp() {
        // Đọc dữ liệu từ Excel
        String excelFilePath = "book.xlsx";
        String sheetName = "Sheet1";
        excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        // Khởi tạo WebDriver và các đối tượng cần thiết
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://saucelabs.com/request-demo/");
    }

    @DataProvider(name = "demoData")
    public Object[][] provideTestData() {
        Object[][] data = new Object[excelData.size()][1];
        for (int i = 0; i < excelData.size(); i++) {
            data[i][0] = excelData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "demoData")
    public void testSubmitWithFullData(Map<String, String> rowData) {
        // Nhập dữ liệu
        driver.findElement(By.id("Email")).sendKeys(rowData.getOrDefault("Business Email", ""));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys(rowData.getOrDefault("First Name", ""));
        driver.findElement(By.id("LastName")).sendKeys(rowData.getOrDefault("Last Name", ""));
        driver.findElement(By.id("Company")).sendKeys(rowData.getOrDefault("Company", ""));
        driver.findElement(By.id("Phone")).sendKeys(rowData.getOrDefault("Phone Number", ""));
        WebElement countryDropdown = driver.findElement(By.id("Country"));
        new Select(countryDropdown).selectByVisibleText(rowData.getOrDefault("Country", ""));
        WebElement interestDropdown = driver.findElement(By.id("Solution_Interest__c"));
        new Select(interestDropdown).selectByVisibleText(rowData.getOrDefault("Interest", ""));
        driver.findElement(By.id("Sales_Contact_Comments__c")).sendKeys(rowData.getOrDefault("Comments", ""));
        driver.findElement(By.id("LblmktoCheckbox_44280_0")).click();
        submitForm();
        System.out.println("Test submit with full data passed.");
    }

    @Test()
    public void testSubmitNull() {
        submitForm();
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Must be valid email')]")));
        String actualMessage = errorMessage.getText();
        Assert.assertEquals(actualMessage, "Must be valid email.\n" +
                "example@yourdomain.com", "Error message does not match " +
                "expected.");
    }

    @Test(dataProvider = "demoData")
    public void testSubmitWithoutEmail(Map<String, String> rowData) {
        ///
    }

    @Test(dataProvider = "demoData")
    public void testSubmitWithoutInterest(Map<String, String> rowData) {
        //
    }

    private void submitForm() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
