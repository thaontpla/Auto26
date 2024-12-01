package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    static WebDriver driver = null;
    public static void main(String[] args) {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/");
        driver.quit();
    }
}