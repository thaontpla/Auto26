package feature.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.RequestDemoPageUI;

public class FindElement {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        //WebElement element = driver.findElement(By.xpath("//input[@id='Email']"));
        WebElement element = driver.findElement(By.id("Email"));
        //WebElement element = driver.findElement(By.name("Email"));
        //WebElement element = driver.findElement(By.className("mktoButton")); //Let's Talk
        //WebElement element = driver.findElement(By.linkText("Try it free"));
        //WebElement element = driver.findElement(By.partialLinkText("Try"));
//        WebElement element = driver.findElement(By.linkText("Try it free"));
        element.sendKeys("aaaaaaaaa");
        element.clear();
        //driver.quit();}
}}

