package feature;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.RequestDemoPageUI;

public class Day2Test {
    private WebDriver driver;
    private RequestDemoPageUI requestDemoPageUI;
    public Day2Test(WebDriver driver) {
        this.driver = driver;
        this.requestDemoPageUI = new RequestDemoPageUI(driver);
    }
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        // Tạo instance của TestDemo
        Day2Test testDemo = new Day2Test(driver);
        // Sử dụng requestDemoPageUI
        //testDemo.requestDemoPageUI.findPasswordInput().sendKeys("email của tôi nè");
        driver.quit();
        // cách dùng luôn mà ko tạo class RequestDemoPageUI
//        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='Email']"));
//        usernameInput.sendKeys("email của tôi nè");
    }
}
