package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageUI { //Lớp LoginPageUI đại diện cho trang Login
    WebDriver driver;
    // Constructor
    public LoginPageUI(WebDriver driver) {
        this.driver = driver;
    }
    //locator
    public WebElement inputUserName() {
        return driver.findElement(By.id("user-name"));
    }
    public WebElement inputPassWord() {
        return driver.findElement(By.id("password"));
    }
    public WebElement buttonLogin() {
        return driver.findElement(By.id("login-button"));
    }
}
