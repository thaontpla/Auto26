package feature.day5;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import untils.ExcelUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddToCartTest {
    public static void main(String[] args) {
        // Đường dẫn file Excel
        String excelFilePath = "dataLogin.xlsx";
        String sheetName = "Sheet1"; // Hoặc thay bằng tên sheet bất kỳ trong file

        // Đọc dữ liệu từ file Excel
        List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

        // Thiết lập WebDriver (trình duyệt Chrome) và login web
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        try {
            // Duyệt qua từng bản ghi trong dữ liệu
            for (Map<String, String> rowData : excelData) {
                System.out.println("Dữ liệu hàng: " + rowData);
                String product = rowData.get("Username"); // Lấy giá trị cột "user"
                //sử dụng nối chuỗi
//                WebElement addToCart = driver.findElement(By.xpath("//div[contains(text(),'" + product + "')" +
//                        "]/ancestor::div[@class='inventory_item_description']/descendant::button"));
                //sử dụng String format
                WebElement addToCart = driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')" +
                        "]/ancestor::div[@class='inventory_item_description']/descendant::button", product)));
                addToCart.click();
            }
        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
    }
}
