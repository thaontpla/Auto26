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

public class ChuaBaiBookYourDemoTest {

    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    //browser chỉ mở 1 lần, áp dun cho tất cả @Test
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    @BeforeMethod
    //url được mở sau lỗi @Test
    public void setUrl() {
        driver.get("https://saucelabs.com/request-demo/");
    }

    @DataProvider(name = "demoData")
    public Object[][] provideTestData() {
        String excelFilePath = "book.xlsx";
        String sheetName = "Sheet1";

        try {
            // Đọc dữ liệu từ file Excel
            List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

            // Chuyển đổi danh sách Map thành mảng 2 chiều
            Object[][] data = new Object[excelData.size()][1];
            for (int i = 0; i < excelData.size(); i++) {
                data[i][0] = excelData.get(i);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi đọc tệp Excel: " + e.getMessage());
        }
    }

    public void fillForm(Map<String, String> rowData) {
        try {
            driver.findElement(By.id("Email")).sendKeys(rowData.get("Business Email"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys(rowData.get("First Name"));
            driver.findElement(By.id("LastName")).sendKeys(rowData.get("Last Name"));
            driver.findElement(By.id("Company")).sendKeys(rowData.get("Company"));
            driver.findElement(By.id("Phone")).sendKeys(rowData.get("Phone Number"));

            Select countryDropdown = new Select(driver.findElement(By.id("Country")));
            countryDropdown.selectByVisibleText(rowData.get("Country"));

            Select interestDropdown = new Select(driver.findElement(By.id("Solution_Interest__c")));
            interestDropdown.selectByVisibleText(rowData.get("Interest"));

            driver.findElement(By.id("Sales_Contact_Comments__c")).sendKeys(rowData.get("Comments"));
            driver.findElement(By.id("LblmktoCheckbox_44280_0")).click();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi điền form: " + e.getMessage());
        }
    }

    @Test(dataProvider = "demoData")
    public void testRequestDemo(Map<String, String> rowData) {
        fillForm(rowData);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Request success for: " + rowData.get("Business Email"));
    }

    @Test
    public void testSubmitNull() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement errorMessage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Must be valid email')]")));
        String actualMessage = errorMessage.getText();
        Assert.assertEquals(actualMessage, "Must be valid email.\nexample@yourdomain.com",
                "Error message does not match expected.");
    }

    @Test(dataProvider = "demoData")
    public void testFirstNameNull(Map<String, String> rowData) {
        rowData.put("First Name", ""); // Đặt First Name trống
        fillForm(rowData);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ValidMsgFirstName']")));
        String actualMessage = errorMessage.getText();
        Assert.assertEquals(actualMessage, "This field is required.");
    }

    @Test(dataProvider = "demoData")
    public void testLastNameNull(Map<String, String> rowData) {
        rowData.put("Last Name", ""); // Đặt Last Name trống
        fillForm(rowData);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorMessage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ValidMsgLastName']")));
        String actualMessage = errorMessage.getText();
        Assert.assertEquals(actualMessage, "This field is required.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
