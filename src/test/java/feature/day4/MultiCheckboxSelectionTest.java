package feature.day4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class MultiCheckboxSelectionTest {
    public static void main(String[] args) {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Mở trang web
        driver.get("https://demo.guru99.com/test/radio.html");

        // Tìm danh sách các checkbox
        List<WebElement> checkboxes = new ArrayList<>();
        checkboxes.add(driver.findElement(By.id("vfb-6-0"))); // Checkbox 1
        checkboxes.add(driver.findElement(By.id("vfb-6-1"))); // Checkbox 2

        // Click vào từng checkbox và in ra giá trị đã chọn
        for (WebElement checkbox : checkboxes) {
            checkbox.click(); // Chọn checkbox
            System.out.println("Checkbox value selected: " + checkbox.getAttribute("value"));
        }

        // Đóng trình duyệt
        driver.quit();
    }
}
