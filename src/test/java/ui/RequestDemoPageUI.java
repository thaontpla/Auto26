package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RequestDemoPageUI {
    private final WebDriver driver;

    public RequestDemoPageUI(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement findPasswordInput() {
        return driver.findElement(By.xpath("//input[@id='Email']"));
    }
}
