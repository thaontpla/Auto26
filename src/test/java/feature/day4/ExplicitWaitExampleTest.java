package feature.day4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitExampleTest {
    public static void main(String[] args) {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Mở trang web
        driver.get("https://saucelabs.com/request-demo");

        // Tạo đối tượng WebDriverWait (15 giây)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Chờ cho phần tử "Email" xuất hiện
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        firstNameField.sendKeys("John@gmail");

        // Chờ cho nút "Submit" có thể nhấp
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitButton.click();

        // Đóng trình duyệt
        driver.quit();
    }
}
