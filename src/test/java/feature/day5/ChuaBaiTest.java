package feature.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import untils.ExcelUtils;

import java.util.List;
import java.util.Map;

public class ChuaBaiTest {
    public static void main(String[] args) {
        // Đường dẫn file Excel
        String excelFilePath = "dataBookYourDemo.xlsx";
        String sheetName = "Sheet1"; // Tên sheet trong file Excel

        // Đọc dữ liệu từ file Excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        // Lặp qua từng bản ghi trong file Excel
        for (Map<String, String> rowData : excelData) {
            System.out.println("Dữ liệu hàng: " + rowData);

            // Lấy dữ liệu từ các cột trong file Excel
            String businessEmail = rowData.getOrDefault("Business Email", "");
            String company = rowData.getOrDefault("Company", "");
            String interest = rowData.getOrDefault("Interest", "");
            String comments = rowData.getOrDefault("Comments", "");

            // Nhập thông tin vào các trường dữ liệu trong form
            driver.findElement(By.id("Email")).sendKeys(businessEmail);
            driver.findElement(By.id("Company")).sendKeys(company);
            driver.findElement(By.xpath("//option[@value='" + interest + "']")).click();
            driver.findElement(By.id("Sales_Contact_Comments__c")).sendKeys(comments);

            // Tích chọn checkbox
            driver.findElement(By.id("LblmktoCheckbox_44280_0")).click();

            // Nhấn nút "Let's Talk"
            driver.findElement(By.xpath("//button[contains(text(),'Talk')]")).click();

            // In ra kết quả
            System.out.println("Yêu cầu demo đã được gửi thành công cho: " + businessEmail);
        }
        driver.quit();
    }
}
