package feature.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import untils.ExcelUtils;

import java.util.List;
import java.util.Map;

public class LoginPageV2Test {
    public static void main(String[] args) {
        // Đường dẫn file Excel
        String excelFilePath = "dataLogin.xlsx";
        String sheetName = "Sheet1"; // Hoặc thay bằng tên sheet bất kỳ trong file
        // Đọc dữ liệu từ file Excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);
        // Thiết lập WebDriver (trình duyệt Chrome)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Duyệt qua từng bản ghi trong dữ liệu
            for (Map<String, String> rowData : excelData) {
                System.out.println("Dữ liệu hàng: " + rowData);
                String user = rowData.get("Username"); // Lấy giá trị cột "user"
                String pass = rowData.get("Password"); // Lấy giá trị cột "pass"
                driver.get("https://www.saucedemo.com/");
                WebElement usernameInput = driver.findElement(By.id("user-name"));
                usernameInput.sendKeys(user);
                WebElement passwordInput = driver.findElement(By.id("password"));
                passwordInput.sendKeys(pass);
            }
        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
    }
}
