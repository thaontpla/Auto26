package feature.day4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

//Implicit Wait sẽ áp dụng một khoảng thời gian chờ mặc định trước khi ném ra lỗi nếu Selenium không tìm thấy phần tử nào.
public class ImplicitWaitExampleTest {
    public static void main(String[] args) {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Áp dụng Implicit Wait (10 giây)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Mở trang web
        driver.get("https://saucelabs.com/request-demo");

        // Tìm phần tử "Email" và nhập dữ liệu
        WebElement firstNameField = driver.findElement(By.id("Email"));
        firstNameField.sendKeys("John@gmail");

        // Tìm và nhấp vào nút "Submit"
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        // Đóng trình duyệt
        driver.quit();
    }
}
