package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RequestDemoPageUI {
    WebDriver driver;

    public void LoginPageUI(WebDriver driver) {
        this.driver = driver;
    }

    public RequestDemoPageUI(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findTexBoxBusinessEmail() {
        return driver.findElement(By.xpath("//input[contains(@id,'Email')]"));
    }
    public WebElement findTexBoxFirstName() {
        return driver.findElement(By.xpath("//input[contains(@id,'FirstName')]"));
    }
    public WebElement labelFirstName() {
        return driver.findElement(By.xpath("//label[@id='LblFirstName']"));
    }
    public WebElement findTexBoxLastName() {
        return driver.findElement(By.xpath("//input[contains(@id,'LastName')]"));
    }
    public WebElement findTexBoxCompany() {
        return driver.findElement(By.xpath("//input[contains(@id,'Company')]"));
    }

    public WebElement findTexBoxPhone() {
        return driver.findElement(By.xpath("//input[contains(@id,'Phone')]"));
    }
    public WebElement findTexBoxCountry() {
        return driver.findElement(By.xpath("//select[contains(@id,'Country')]"));
    }

    public WebElement findTexBoxInterest() {
        return driver.findElement(By.xpath("//select[contains(@id,'Solution')]"));
    }
    public WebElement findTexBoxComment() {
        return driver.findElement(By.xpath("//textarea[contains(@id,'Sales')]"));
    }
    public WebElement findCheckBox() {
        return driver.findElement(By.xpath("//input[contains(@type,'checkbox')]"));
    }

    public WebElement findButtonLetsTalk() {
        return driver.findElement(By.xpath("//button[contains(@type,'sub')]"));
    }
}
