package feature.day4;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class JavaScriptExecutorExampleTest {
    public static void main(String[] args) {

        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Mở trang web
        driver.get("https://saucelabs.com/request-demo");

        // Lấy phần tử droplist (interest) bằng cách dùng XPath
        WebElement interestDropdown = driver.findElement(By.id("Solution_Interest__c"));

        // Tạo đối tượng JavascriptExecutor.  chuyển đổi driver thành JavascriptExecutor để thực thi các đoạn mã JavaScript trong trình duyệt.
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // thực thi đoạn mã JavaScript và thay đổi giá trị của dropdown
        // Thực hiện thay đổi giá trị (giả sử chúng ta chọn một option có giá trị là "Visual Testing")
        js.executeScript("arguments[0].value='Visual Testing';", interestDropdown);

        // Kiểm tra giá trị đã được chọn
        String selectedValue = interestDropdown.getAttribute("value");
        System.out.println("Selected value from droplist: " + selectedValue);

        // Đóng trình duyệt
        driver.quit();
    }
}
