package feature.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class SwitchTabsExample {
    public static void main(String[] args) throws InterruptedException {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Mở trang web đầu tiên
        driver.get("https://saucelabs.com/request-demo");
        System.out.println("Tab 1 title: " + driver.getTitle());

        // Lưu ID của tab gốc
        String originalTab = driver.getWindowHandle();

        // Mở tab mới để truy cập Google
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.open('https://google.com', '_blank');");

        // Lấy tất cả các ID cửa sổ/tab
        Set<String> allTabs = driver.getWindowHandles();

        // Chuyển sang tab mới
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        // Thao tác trên tab mới (Google)
        System.out.println("Tab 2 title: " + driver.getTitle());
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Sauce Labs Request Demo");
        searchBox.submit();
        Thread.sleep(3000); // Đợi trang tải

        // Quay lại tab ban đầu
        driver.switchTo().window(originalTab);
        System.out.println("Back to Tab 1 title: " + driver.getTitle());

        // Tương tác với form trên trang gốc
        WebElement element = driver.findElement(By.id("Email"));
        element.sendKeys("test@gmail");
        // Đóng tất cả các tabs
        driver.quit();
    }
}
