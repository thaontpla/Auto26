package feature.day07;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.LoginPageUI;

public class ModuleDrivenTest {
    public WebDriver driver;
    public LoginPageUI loginPageUI;
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUrl() {
        driver.get("https://www.saucedemo.com/");
        loginPageUI = new LoginPageUI(driver);
    }
    public void inputData(String user,String pass){
        loginPageUI.inputUserName().sendKeys(user);
        loginPageUI.inputPassWord().sendKeys(pass);
    }
    public void clickLogin(){
        loginPageUI.buttonLogin().click();
    }
    @Test
    public void inputValidUserNameAndPassword() {
        inputData("standard_user","secret_sauce");
        clickLogin();
    }

    @Test
    public void inputValidUserNameAndInvalidPassword() {
        inputData("standard_user","secret_sauce1");
        clickLogin();
        WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test
    public void inputInvalidUserNameAndValidPassword() {
        inputData("standard_user1","secret_sauce");
        clickLogin();
        WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
    }

    @Test
    public void inputInvalidUserNameAndInvalidPassword() {
        inputData("standard_user1","secret_sauce1");
        clickLogin();
        WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
