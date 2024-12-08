package feature;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.RequestDemoPageUI;

public class Day2V1Test {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        // Khởi tạo requestDemoPageUI
        RequestDemoPageUI requestDemoPageUI = new RequestDemoPageUI(driver);
        // Sử dụng requestDemoPageUI
        requestDemoPageUI.findPasswordInput().sendKeys("email của tôi nè");

        driver.quit();
    }
}
