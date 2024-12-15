package feature.day4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class SingleRadioButtonSelectionTest {
    public static void main(String[] args) {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Mở trang web
        driver.get("https://demo.guru99.com/test/radio.html");

        // Tìm một Radio Button
        WebElement radioButton = driver.findElement(By.id("vfb-7-2")); // Radio Button 2

        // Click vào radio button đã chọn
        radioButton.click();

        // Lấy giá trị và trạng thái của radio button
        String value = radioButton.getAttribute("value");
        boolean isSelected = radioButton.isSelected();

        // In ra giá trị và trạng thái đã chọn
        System.out.println("Radio Button value selected: " + value);
        System.out.println("Radio Button is selected: " + isSelected);

        // Đóng trình duyệt
        driver.quit();
    }
}
