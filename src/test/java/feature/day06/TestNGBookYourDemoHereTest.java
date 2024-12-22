package feature.day06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import untils.ExcelUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class TestNGBookYourDemoHereTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @DataProvider(name = "demoData")
    public Object[][] provideTestData() {
        String excelFilePath = "book.xlsx";
        String sheetName = "Sheet1";
        // Đọc dữ liệu từ file Excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        // Chuyển đổi danh sách Map thành mảng 2 chiều
        Object[][] data = new Object[excelData.size()][1];
        for (int i = 0; i < excelData.size(); i++) {
            data[i][0] = excelData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "demoData")
    public void testRequestDemo(Map<String, String> rowData) {
        driver.get("https://saucelabs.com/request-demo/");
        // Lấy dữ liệu từ từng bản ghi
        String businessEmail = rowData.get("Business Email");
        String firstName = rowData.get("First Name");
        String lastName = rowData.get("Last Name");
        String company = rowData.get("Company");
        String phoneNumber = rowData.get("Phone Number");
        String country = rowData.get("Country");
        String interest = rowData.get("Interest");
        String comments = rowData.get("Comments");

        // Nhập dữ liệu và thực hiện các thao tác trên trang
        driver.findElement(By.id("Email")).sendKeys(businessEmail);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Company")).sendKeys(company);
        driver.findElement(By.id("Phone")).sendKeys(phoneNumber);

        WebElement countryDropdownList = driver.findElement(By.id("Country"));
        new Select(countryDropdownList).selectByVisibleText(country);

        WebElement interestDropdownList = driver.findElement(By.id("Solution_Interest__c"));
        new Select(interestDropdownList).selectByVisibleText(interest);

        driver.findElement(By.id("Sales_Contact_Comments__c")).sendKeys(comments);
        driver.findElement(By.id("LblmktoCheckbox_44280_0")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        System.out.println("Request success for: " + businessEmail);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
