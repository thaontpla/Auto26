package feature.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BTVNTest {
    private static WebDriver driver;

    public BTVNTest(WebDriver driver) {
        this.driver = driver;
    }

    public static void main(String[] args) {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        BTVNTest inventory = new BTVNTest(driver);

        inventory.login();

        inventory.addItemButton();

        inventory.checkout();

        driver.quit();
    }

    public static void login() {
        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();
    }

    public static void addItemButton() {
        WebElement addItemButton1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addItemButton1.click();

        WebElement addItemButton2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        addItemButton2.click();

        WebElement addItemButton3 = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addItemButton3.click();
    }

    public static void checkout() {
        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        shoppingCart.click();

        WebElement buttonCheckout = driver.findElement(By.id("checkout"));
        buttonCheckout.click();

        WebElement inputFirstName = driver.findElement(By.id("first-name"));
        inputFirstName.sendKeys("Hoang Thai");

        WebElement inputLastName = driver.findElement(By.id("last-name"));
        inputLastName.sendKeys("Ha");

        WebElement inputZip = driver.findElement(By.id("postal-code"));
        inputZip.sendKeys("123456");

        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();

        WebElement buttonFinish = driver.findElement(By.id("finish"));
        buttonFinish.click();

        WebElement messageComplete = driver.findElement(By.xpath("//span[@class='title']"));
        System.out.println("Message: " + messageComplete.getText());
    }
}
