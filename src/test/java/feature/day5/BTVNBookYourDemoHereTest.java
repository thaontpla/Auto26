package feature.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import untils.ExcelUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BTVNBookYourDemoHereTest {
    public static void main(String[] args) {

//        Đường dẫn file excel
        String excelFilePath = "book.xlsx";
        String sheetName = "Sheet1"; //Hoặc thay bằng tên sheet bất kỳ trong file

//        Đọc dữ liệu từ file excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Khởi tạo WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://saucelabs.com/request-demo/");
//            Duyệt qua từng bản ghi trong dữ liệu
            for (Map<String, String> rowData : excelData) {
                System.out.println("Dữ liệu hàng: " + rowData);
                String businessEmail = rowData.get("Business Email"); //Lấy giá trị cột Business Email
                String firstName = rowData.get("First Name");
                String lastName = rowData.get("Last Name");
                String company = rowData.get("Company");
                String phoneNumber = rowData.get("Phone Number");
                String country = rowData.get("Country");
                String interest = rowData.get("Interest");
                String comments = rowData.get("Comments");

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

                System.out.println("Request success");
            }
            driver.quit();
    }

}
